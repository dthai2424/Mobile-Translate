DROP DATABASE IF EXISTS mobile_translator;
CREATE DATABASE mobile_translator;
USE mobile_translator;
DROP TABLE IF EXISTS saved_translation;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255),
                       username VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(50) DEFAULT 'USER',
                       created_at DATETIME NOT NULL,
                       active TINYINT(1) DEFAULT 1
);


CREATE TABLE language (
                          language_id VARCHAR(50) PRIMARY KEY, -- String ID
                          language_name VARCHAR(255),
                          active TINYINT(1) DEFAULT 1
);


CREATE TABLE saved_translation (
                                   saved_translation_id INT AUTO_INCREMENT PRIMARY KEY,
                                   user_id INT NOT NULL,
                                   source_language_id VARCHAR(50) NOT NULL,
                                   source_text TEXT,
                                   target_language_id VARCHAR(50) NOT NULL,
                                   target_text TEXT,
                                   created_at DATETIME NOT NULL,
                                   active TINYINT(1) DEFAULT 1,


                                   CONSTRAINT fk_saved_translation_user FOREIGN KEY (user_id)
                                       REFERENCES users(user_id) ON DELETE CASCADE,
                                   CONSTRAINT fk_saved_translation_source_lang FOREIGN KEY (source_language_id)
                                       REFERENCES language(language_id),
                                   CONSTRAINT fk_saved_translation_target_lang FOREIGN KEY (target_language_id)
                                       REFERENCES language(language_id)
);


INSERT INTO language (language_id, language_name, active) VALUES
                                                              ('en', 'English', 1),
                                                              ('vi', 'Vietnamese', 1),
                                                              ('ja', 'Japanese', 1),
                                                              ('ko', 'Korean', 1);


INSERT INTO users (email, username, password, role, created_at, active) VALUES
    ('admin@mobiletranslate.app', 'admin', '$2a$10$xyz...', 'ADMIN', NOW(), 1);