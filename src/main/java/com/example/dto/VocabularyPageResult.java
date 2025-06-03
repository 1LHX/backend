package com.example.dto;

import com.example.pojo.Vocabulary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyPageResult {
    private List<Vocabulary> vocabularies;
    private int totalPages;
    private long totalWords;
    private int currentPage;
    private int pageSize;
}
