package com.web.c101.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ElasticUtil {
    private static ElasticUtil self;
    private RestClientBuilder restClientBuilder;

    private ElasticUtil() {

        String hostname = "http://3.34.51.207";
        int port = 9200;
        HttpHost host = new HttpHost(hostname, port);
        restClientBuilder = RestClient.builder(new HttpHost("3.34.51.207", 9200))
            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(
                        HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder
                            .setDefaultCredentialsProvider(getCredentialsProvider());
                }
            });
    };

    public static ElasticUtil getInstance() {
        if(self == null)
            self = new ElasticUtil();
        return self;
    }

    public CredentialsProvider getCredentialsProvider(){
        CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "msg!234"));
        return credentialsProvider;
    }

    public List<Map<String,Object>> ESSearch(
            String index
            , Map<String,Object> query
            , Map<String,SortOrder> sort
    ){

        // search에 index 조건 걸기
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // query에 있는 셋 쿼리 조건으로 걸기
        for(String key : query.keySet()) {
            searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery(key, "*" + query.get(key) + "*")));
        }

        // sort 에 있는 셋을 정렬 조건으로 걸기
        for(String key : sort.keySet()) {
            searchSourceBuilder.sort(new FieldSortBuilder(key).order(sort.get(key)));
        }

        searchRequest.source(searchSourceBuilder);

        List<Map<String,Object>> list = new ArrayList<>();
        try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();
            for(SearchHit hit : searchHits) {
                Map<String, Object> sourceMap = hit.getSourceAsMap();
                list.add(sourceMap);
            }
        } catch (IOException e) {}

        return list;

    }

    public boolean updateCnt(String index, String name, String area) {

        System.out.println(name + " : " + area);

        // 해당 doc의 version 가져오기
        String id;
        // search에 index 조건 걸기
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // query에 있는 셋 쿼리 조건으로 걸기
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name)));
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("area", area)));

        searchRequest.source(searchSourceBuilder);

        try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();

            if(searchHits.getHits().length == 0) {
                System.out.println("처음 검색된 맛집");

                Map<String, Object> body = new HashMap<>();
                body.put("name", name);
                body.put("area", area);
                body.put("cnt", 1);

                insert("realtime", body);
            } else {

                System.out.println(searchHits.getHits()[0].getId());
                id = searchHits.getHits()[0].getId();
                int cnt = (int) searchHits.getHits()[0].getSourceAsMap().get("cnt");

                // 해당 id의 doc를 update
                Map<String, Object> body = new HashMap<>();
                body.put("name", name);
                body.put("area", area);
                body.put("cnt", cnt + 1);
                UpdateRequest updateRequest = new UpdateRequest("realtime", id).doc(body);

                client.update(updateRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public int insert(String index, Map<String, Object> data ){

        IndexResponse response = null;

        try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {

            XContentBuilder xContent = XContentFactory.jsonBuilder().map(data);
            String jsonBody = Strings.toString(xContent);

            IndexRequest indexRequest = new IndexRequest(index).source(jsonBody, XContentType.JSON);

            response = client.index(indexRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {}

        return response.getShardInfo().getSuccessful();
    }

}
