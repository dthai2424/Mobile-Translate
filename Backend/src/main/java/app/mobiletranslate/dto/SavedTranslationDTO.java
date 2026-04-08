package app.mobiletranslate.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SavedTranslationDTO {
    private int userId;
    private String sourceLangId;
    private String sourceText;
    private String targetLangId;
    private String targetText;
    private boolean active;
}
