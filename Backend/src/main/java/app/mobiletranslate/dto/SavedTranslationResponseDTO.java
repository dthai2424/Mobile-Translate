package app.mobiletranslate.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavedTranslationResponseDTO {
    private Integer savedTranslationId;
    private String sourceLang;
    private String sourceText;
    private String targetLang;
    private String targetText;
    private LocalDateTime createdAt;
}