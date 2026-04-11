package app.mobiletranslate.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDTO {
    String languageId;
    String languageName;
    boolean active;
}
