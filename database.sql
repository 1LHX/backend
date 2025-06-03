-- 创建数据库
CREATE DATABASE IF NOT EXISTS backend DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE backend;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建词汇表
CREATE TABLE IF NOT EXISTS vocabulary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(100) NOT NULL UNIQUE,
    meaning TEXT NOT NULL
);

-- 创建错题本表
CREATE TABLE IF NOT EXISTS error_vocabulary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    word_id INT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_user_word (user_id, word_id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (word_id) REFERENCES vocabulary (id) ON DELETE CASCADE
);

-- 插入测试数据（密码是 123456 的加密结果）
INSERT INTO
    user (username, password, email)
VALUES (
        'admin',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8ioctKi85KTa8fy8r8yz8wezuO.2C',
        'admin@example.com'
    ),
    (
        'test',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8ioctKi85KTa8fy8r8yz8wezuO.2C',
        'test@example.com'
    );

-- 插入测试词汇数据
INSERT INTO
    vocabulary (word, meaning)
VALUES ('apple', '苹果'),
    ('book', '书籍'),
    ('computer', '计算机'),
    ('dog', '狗'),
    ('elephant', '大象'),
    ('flower', '花朵'),
    ('guitar', '吉他'),
    ('house', '房子'),
    ('ice', '冰'),
    ('jungle', '丛林');