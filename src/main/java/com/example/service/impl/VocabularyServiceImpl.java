package com.example.service.impl;

import com.example.dto.VocabularyPageResult;
import com.example.mapper.VocabularyMapper;
import com.example.pojo.Vocabulary;
import com.example.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Override
    public Vocabulary getVocabularyById(Integer id) {
        return vocabularyMapper.getVocabularyById(id);
    }

    @Override
    public List<Vocabulary> getAllWords() {
        return vocabularyMapper.getAllWords();
    }

    @Override
    public VocabularyPageResult getWordsByPage(int page, int pageSize) {
        // 计算偏移量
        int offset = page * pageSize;

        // 获取分页数据
        List<Vocabulary> vocabularies = vocabularyMapper.getWordsByPage(offset, pageSize);

        // 获取总数
        long totalWords = vocabularyMapper.getTotalWordsCount();

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalWords / pageSize);

        return new VocabularyPageResult(vocabularies, totalPages, totalWords, page, pageSize);
    }

    @Override
    public List<Vocabulary> getRandomWords(int count) {
        return vocabularyMapper.getRandomWords(count);
    }

    @Override
    public List<Vocabulary> searchVocabularyByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of(); // 返回空列表
        }
        return vocabularyMapper.searchVocabularyByKeyword(keyword.trim());
    }
}
