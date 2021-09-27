package com.web.c101.search;


import com.web.c101.BasicResponse;
import com.web.c101.util.ElasticUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class SearchController {

    ElasticUtil elastic = ElasticUtil.getInstance();

    @GetMapping("/search")
    @ApiOperation(value = "검색하기")
    public Object searchStore(@RequestParam String name) {
        System.out.println("name : " + name);

        log.info("검색하기");
        BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "fail";

        Map<String, Object> query = new HashMap<>();
        query.put("name", name);

        List<Map<String, Object>> list = elastic.simpleSearch("msg", query);

        if(list.size() > 0){
            result.status = true;
            result.data = "success";
            result.object = list;
        }

        return result;

    }

}
