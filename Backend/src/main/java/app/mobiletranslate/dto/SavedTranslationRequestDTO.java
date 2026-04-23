package app.mobiletranslate.dto;

import lombok.Data;

@Data
public class SavedTranslationRequestDTO {
    private String sourceLang;
    private String sourceText;
    private String targetLang;
    private String targetText;
}