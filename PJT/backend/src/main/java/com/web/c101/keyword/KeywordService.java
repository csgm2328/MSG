package com.web.c101.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Resource(name = "sentimentDict")
    private final Map<String, Integer> sentimentDict;

    public List<AnalyzedKeyword> getKeywords(String store) {
        List<KeywordDto> keywords = keywordRepository.getKeywords(store);

        if (keywords.size() > 20) {
            keywords = reduceKeywords(keywords);
            keywordRepository.replaceKeywords(store, keywords);
        }

        List<AnalyzedKeyword> analyzedKeywords = new ArrayList<>();
        for (KeywordDto keyword : keywords) {
            String sentiment = "없음";
            if (sentimentDict.containsKey(keyword.getWord())) {
                sentiment = sentimentToString(sentimentDict.get(keyword.getWord()));
            }
            analyzedKeywords.add(AnalyzedKeyword.keywordToAnalyzed(keyword, sentiment));
        }

        return analyzedKeywords;
    }

    private String sentimentToString(int senti) {
        String sentiment = "";
        if (senti == 2)
            sentiment = "매우 긍정";
        else if (senti == 1)
            sentiment = "긍정";
        else if (senti == 0)
            sentiment = "중립";
        else if (senti == -1)
            sentiment = "부정";
        else if (senti == -2)
            sentiment = "매우 부정";

        return sentiment;
    }

    private List<KeywordDto> reduceKeywords(List<KeywordDto> keywords) {
        Collections.sort(keywords, new KeywordComparator());
        return new ArrayList<>(keywords.subList(0, 20));
    }

    private static class KeywordComparator implements Comparator<KeywordDto> {
        @Override
        public int compare(KeywordDto o1, KeywordDto o2) {
            return Integer.compare(Integer.parseInt(o1.getCount()), Integer.parseInt(o2.getCount()));
        }
    }
}