package com.example.service;

import java.util.List;

public interface ErrorVocabularyService {

    /**
     * 根据用户ID获取所有错题的词汇ID
     */
    List<Integer> getWordIdsByUserId(Integer userId);

    /**
     * 添加错题记录
     */
    String addErrorVocabulary(Integer userId, Integer wordId);

    /**
     * 删除指定用户的指定错题
     */
    String deleteErrorVocabulary(Integer userId, Integer wordId);

    /**
     * 删除指定用户的所有错题
     */
    String deleteAllErrorVocabularyByUser(Integer userId);
}
