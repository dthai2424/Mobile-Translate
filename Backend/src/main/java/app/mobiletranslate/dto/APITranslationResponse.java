package app.mobiletranslate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class APITranslationResponse {
    @JsonProperty("translatedText")
    private String targetText;
}
