package com.example.service;

import com.example.dto.VocabularyPageResult;
import com.example.pojo.Vocabulary;

import java.util.List;

public interface VocabularyService {

    /**
     * 根据ID获取单词和含义
     */
    Vocabulary getVocabularyById(Integer id);

    /**
     * 获取所有单词列表（包含单词和含义）
     */
    List<Vocabulary> getAllWords();

    /**
     * 分页获取单词列表（包含单词和含义）
     */
    VocabularyPageResult getWordsByPage(int page, int pageSize);

    /**
     * 随机获取指定数量的单词（包含单词和含义）
     */
    List<Vocabulary> getRandomWords(int count);

    /**
     * 根据关键字模糊匹配查询单词和含义
     */
    List<Vocabulary> searchVocabularyByKeyword(String keyword);
}
