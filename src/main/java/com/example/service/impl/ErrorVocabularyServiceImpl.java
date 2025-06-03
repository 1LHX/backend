package com.example.service.impl;

import com.example.mapper.ErrorVocabularyMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.VocabularyMapper;
import com.example.pojo.ErrorVocabulary;
import com.example.pojo.User;
import com.example.pojo.Vocabulary;
import com.example.service.ErrorVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorVocabularyServiceImpl implements ErrorVocabularyService {

    @Autowired
    private ErrorVocabularyMapper errorVocabularyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Override
    public List<Integer> getWordIdsByUserId(Integer userId) {
        // 验证用户是否存在
        User user = userMapper.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        return errorVocabularyMapper.getWordIdsByUserId(userId);
    }

    @Override
    public String addErrorVocabulary(Integer userId, Integer wordId) {
        // 验证用户是否存在
        User user = userMapper.getUserById(userId);
        if (user == null) {
            return "用户不存在";
        }

        // 验证词汇是否存在
        Vocabulary vocabulary = vocabularyMapper.getVocabularyById(wordId);
        if (vocabulary == null) {
            return "词汇不存在";
        }

        // 检查错题是否已存在
        if (errorVocabularyMapper.existsErrorVocabulary(userId, wordId)) {
            return "该错题已存在";
        }

        // 添加错题
        ErrorVocabulary errorVocabulary = new ErrorVocabulary(userId, wordId);
        errorVocabularyMapper.addErrorVocabulary(errorVocabulary);

        return "错题添加成功";
    }

    @Override
    public String deleteErrorVocabulary(Integer userId, Integer wordId) {
        // 验证用户是否存在
        User user = userMapper.getUserById(userId);
        if (user == null) {
            return "用户不存在";
        }

        // 检查错题是否存在
        if (!errorVocabularyMapper.existsErrorVocabulary(userId, wordId)) {
            return "该错题不存在";
        }

        // 删除错题
        errorVocabularyMapper.deleteErrorVocabulary(userId, wordId);

        return "错题删除成功";
    }

    @Override
    public String deleteAllErrorVocabularyByUser(Integer userId) {
        // 验证用户是否存在
        User user = userMapper.getUserById(userId);
        if (user == null) {
            return "用户不存在";
        }

        // 删除用户所有错题
        errorVocabularyMapper.deleteAllErrorVocabularyByUserId(userId);

        return "所有错题删除成功";
    }
}
