package app.mobiletranslate.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Language")
public class Language {
    @Id
    private String languageId;
    private String languageName;
    private boolean active;
}
