package app.mobiletranslate.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TranslationRequestDTO {
    private int userId;
    private String sourceLangId;
    private String sourceText;

    private String targetLangId;
}
