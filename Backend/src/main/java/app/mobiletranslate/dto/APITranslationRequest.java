package app.mobiletranslate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class APITranslationRequest {
    @JsonProperty("source")
    private String sourceLangId;
    @JsonProperty("q")
    private String sourceText;
    @JsonProperty("target")
    private String targetLangId;


    private String api_key;

}
