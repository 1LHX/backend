package com.example.mapper;

import com.example.pojo.ErrorVocabulary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ErrorVocabularyMapper {

    /**
     * 根据用户ID获取所有错题的词汇ID
     */
    List<Integer> getWordIdsByUserId(@Param("userId") Integer userId);

    /**
     * 添加错题记录
     */
    void addErrorVocabulary(ErrorVocabulary errorVocabulary);

    /**
     * 删除指定用户的指定错题
     */
    void deleteErrorVocabulary(@Param("userId") Integer userId, @Param("wordId") Integer wordId);

    /**
     * 删除指定用户的所有错题
     */
    void deleteAllErrorVocabularyByUserId(@Param("userId") Integer userId);

    /**
     * 检查错题是否已存在
     */
    boolean existsErrorVocabulary(@Param("userId") Integer userId, @Param("wordId") Integer wordId);
}
