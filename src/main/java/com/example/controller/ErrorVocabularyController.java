package com.example.controller;

import com.example.common.Result;
import com.example.service.ErrorVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/error-vocabulary")
@CrossOrigin
public class ErrorVocabularyController {

    @Autowired
    private ErrorVocabularyService errorVocabularyService;

    /**
     * 根据用户ID获取所有错题的词汇ID
     * 
     * @param userId 用户ID
     * @return 错题词汇ID列表
     */
    @GetMapping("/words")
    public Result getWordIdsByUser(@RequestParam Integer userId) {
        if (userId == null || userId <= 0) {
            return Result.error("用户ID无效");
        }

        try {
            List<Integer> wordIds = errorVocabularyService.getWordIdsByUserId(userId);
            return Result.success(wordIds);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加错题记录
     * 
     * @param userId 用户ID
     * @param wordId 词汇ID
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result addErrorVocabulary(@RequestParam Integer userId,
            @RequestParam Integer wordId) {
        if (userId == null || userId <= 0) {
            return Result.error("用户ID无效");
        }
        if (wordId == null || wordId <= 0) {
            return Result.error("词汇ID无效");
        }

        String result = errorVocabularyService.addErrorVocabulary(userId, wordId);
        if ("错题添加成功".equals(result)) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }

    /**
     * 删除指定错题记录
     * 
     * @param userId 用户ID
     * @param wordId 词汇ID
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result deleteErrorVocabulary(@RequestParam Integer userId,
            @RequestParam Integer wordId) {
        if (userId == null || userId <= 0) {
            return Result.error("用户ID无效");
        }
        if (wordId == null || wordId <= 0) {
            return Result.error("词汇ID无效");
        }

        String result = errorVocabularyService.deleteErrorVocabulary(userId, wordId);
        if ("错题删除成功".equals(result)) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }

    /**
     * 删除指定用户的所有错题记录
     * 
     * @param userId 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/delete-all")
    public Result deleteAllErrorVocabulary(@RequestParam Integer userId) {
        if (userId == null || userId <= 0) {
            return Result.error("用户ID无效");
        }

        String result = errorVocabularyService.deleteAllErrorVocabularyByUser(userId);
        if ("所有错题删除成功".equals(result)) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }
}
