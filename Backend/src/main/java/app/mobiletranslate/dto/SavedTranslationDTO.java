package app.mobiletranslate.dto;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SavedTranslationDTO {
    private int savedTranslationId;
    private int userId;
    private String sourceLangId;
    private String sourceText;
    private String targetLangId;
    private String targetText;
    private LocalDateTime createdAt;

}
