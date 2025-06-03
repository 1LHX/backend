package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorVocabulary {
    private Integer id;
    private Integer userId;
    private Integer wordId;
    private LocalDateTime createTime;

    // 构造函数，用于添加错题时
    public ErrorVocabulary(Integer userId, Integer wordId) {
        this.userId = userId;
        this.wordId = wordId;
    }
}
