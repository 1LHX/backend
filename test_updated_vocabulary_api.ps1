# 测试修改后的单词接口 - 现在返回包含meaning的完整单词信息

$baseUrl = "http://localhost:8080"

Write-Host "=== 测试修改后的单词接口 ===" -ForegroundColor Green

# 测试1: 获取所有单词列表 (现在返回完整的Vocabulary对象)
Write-Host "`n1. 测试获取所有单词列表:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/vocabulary/words" -Method GET
    Write-Host "状态: 成功" -ForegroundColor Green
    Write-Host "返回数据类型: $($response.data[0].GetType().Name)"
    Write-Host "前3个单词示例:"
    for ($i = 0; $i -lt [Math]::Min(3, $response.data.Count); $i++) {
        Write-Host "  ID: $($response.data[$i].id), Word: $($response.data[$i].word), Meaning: $($response.data[$i].meaning)"
    }
    Write-Host "总数量: $($response.data.Count)"
}
catch {
    Write-Host "错误: $($_.Exception.Message)" -ForegroundColor Red
}

# 测试2: 分页获取单词列表 (现在返回完整的Vocabulary对象)
Write-Host "`n2. 测试分页获取单词列表:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/vocabulary/words/page?page=0&pageSize=5" -Method GET
    Write-Host "状态: 成功" -ForegroundColor Green
    Write-Host "分页信息:"
    Write-Host "  当前页: $($response.data.currentPage)"
    Write-Host "  页面大小: $($response.data.pageSize)"
    Write-Host "  总页数: $($response.data.totalPages)"
    Write-Host "  总单词数: $($response.data.totalWords)"
    Write-Host "本页单词:"
    foreach ($vocab in $response.data.vocabularies) {
        Write-Host "  ID: $($vocab.id), Word: $($vocab.word), Meaning: $($vocab.meaning)"
    }
}
catch {
    Write-Host "错误: $($_.Exception.Message)" -ForegroundColor Red
}

# 测试3: 随机获取单词 (现在返回完整的Vocabulary对象)
Write-Host "`n3. 测试随机获取单词:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/vocabulary/words/random?count=3" -Method GET
    Write-Host "状态: 成功" -ForegroundColor Green
    Write-Host "随机单词:"
    foreach ($vocab in $response.data) {
        Write-Host "  ID: $($vocab.id), Word: $($vocab.word), Meaning: $($vocab.meaning)"
    }
}
catch {
    Write-Host "错误: $($_.Exception.Message)" -ForegroundColor Red
}

# 测试4: 根据ID获取单词 (保持不变)
Write-Host "`n4. 测试根据ID获取单词:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/vocabulary/1" -Method GET
    Write-Host "状态: 成功" -ForegroundColor Green
    Write-Host "单词信息: ID: $($response.data.id), Word: $($response.data.word), Meaning: $($response.data.meaning)"
}
catch {
    Write-Host "错误: $($_.Exception.Message)" -ForegroundColor Red
}

# 测试5: 搜索单词 (保持不变)
Write-Host "`n5. 测试搜索单词:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/vocabulary/search?keyword=apple" -Method GET
    Write-Host "状态: 成功" -ForegroundColor Green
    Write-Host "搜索结果:"
    foreach ($vocab in $response.data) {
        Write-Host "  ID: $($vocab.id), Word: $($vocab.word), Meaning: $($vocab.meaning)"
    }
}
catch {
    Write-Host "错误: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== 测试完成 ===" -ForegroundColor Green
Write-Host "修改总结:" -ForegroundColor Cyan
Write-Host "1. /vocabulary/words - 现在返回包含meaning的完整Vocabulary对象列表" -ForegroundColor Cyan
Write-Host "2. /vocabulary/words/page - 现在返回包含meaning的完整Vocabulary对象分页结果" -ForegroundColor Cyan
Write-Host "3. /vocabulary/words/random - 现在返回包含meaning的完整Vocabulary对象列表" -ForegroundColor Cyan
Write-Host "4. /vocabulary/{id} - 保持不变，返回包含meaning的单个Vocabulary对象" -ForegroundColor Cyan
Write-Host "5. /vocabulary/search - 保持不变，返回包含meaning的Vocabulary对象列表" -ForegroundColor Cyan
