package com.web.c101.keyword;

import lombok.Builder;

@Builder
public class AnalyzedKeyword {
    private String keyword;
    private int count;
    private String sentiment;

    public static AnalyzedKeyword keywordToAnalyzed(KeywordDto keywordDto, String sentiment) {
        return AnalyzedKeyword.builder()
                .keyword(keywordDto.getWord())
                .count(Integer.parseInt(keywordDto.getCount()))
                .sentiment(sentiment)
                .build();
    }
}