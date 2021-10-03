package com.web.c101.keyword;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class KeywordRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String STORE_PREFIX = "store:";

    public List<KeywordDto> getKeywords(String store) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return KeywordDto.mapToList(hashOperations.entries(STORE_PREFIX + store));
    }

    public void replaceKeywords(String store, List<KeywordDto> keywords) {
        redisTemplate.delete(STORE_PREFIX + store);

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(STORE_PREFIX + store, KeywordDto.listToMap(keywords));
    }
}
