package app.mobiletranslate.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TranslationResponseDTO {
    private int userId;

    private String targetText;
}
