package com.example.controller;

import com.example.common.Result;
import com.example.dto.VocabularyPageResult;
import com.example.pojo.Vocabulary;
import com.example.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocabulary")
@CrossOrigin
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    /**
     * 根据ID获取单词和含义
     * 
     * @param id 单词ID
     * @return 单词信息（包含word和meaning）
     */
    @GetMapping("/{id}")
    public Result getVocabularyById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return Result.error("单词ID无效");
        }

        Vocabulary vocabulary = vocabularyService.getVocabularyById(id);
        if (vocabulary != null) {
            return Result.success(vocabulary);
        }
        return Result.error("单词不存在");
    }

    /**
     * 获取所有单词列表
     * 
     * @return 单词列表（包含word和meaning）
     */
    @GetMapping("/words")
    public Result getAllWords() {
        List<Vocabulary> words = vocabularyService.getAllWords();
        return Result.success(words);
    }

    /**
     * 分页获取单词列表
     * 
     * @param page     页码（从0开始）
     * @param pageSize 每页大小，默认10
     * @return 分页结果
     */
    @GetMapping("/words/page")
    public Result getWordsByPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (page < 0) {
            return Result.error("页码不能小于0");
        }
        if (pageSize <= 0 || pageSize > 100) {
            return Result.error("页面大小必须在1-100之间");
        }

        VocabularyPageResult result = vocabularyService.getWordsByPage(page, pageSize);
        return Result.success(result);
    }

    /**
     * 随机获取指定数量的单词
     * 
     * @param count 数量，默认5
     * @return 随机单词列表（包含word和meaning）
     */
    @GetMapping("/words/random")
    public Result getRandomWords(@RequestParam(defaultValue = "5") int count) {
        if (count <= 0 || count > 50) {
            return Result.error("数量必须在1-50之间");
        }

        List<Vocabulary> words = vocabularyService.getRandomWords(count);
        return Result.success(words);
    }

    /**
     * 根据关键字模糊匹配查询单词和含义
     * 
     * @param keyword 查询关键字
     * @return 匹配的单词列表（包含word和meaning）
     */
    @GetMapping("/search")
    public Result searchVocabulary(@RequestParam String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Result.error("查询关键字不能为空");
        }

        if (keyword.trim().length() > 100) {
            return Result.error("查询关键字长度不能超过100个字符");
        }

        List<Vocabulary> vocabularies = vocabularyService.searchVocabularyByKeyword(keyword);
        return Result.success(vocabularies);
    }
}
