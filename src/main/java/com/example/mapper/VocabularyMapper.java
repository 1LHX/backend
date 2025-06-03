package com.example.mapper;

import com.example.pojo.Vocabulary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VocabularyMapper {

    /**
     * 根据ID获取单词
     */
    Vocabulary getVocabularyById(Integer id);

    /**
     * 获取所有单词列表（包含单词和含义）
     */
    List<Vocabulary> getAllWords();

    /**
     * 分页获取单词列表（包含单词和含义）
     */
    List<Vocabulary> getWordsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 获取单词总数
     */
    long getTotalWordsCount();

    /**
     * 随机获取指定数量的单词（包含单词和含义）
     */
    List<Vocabulary> getRandomWords(@Param("count") int count);

    /**
     * 根据关键字模糊匹配查询单词和含义
     */
    List<Vocabulary> searchVocabularyByKeyword(@Param("keyword") String keyword);
}
