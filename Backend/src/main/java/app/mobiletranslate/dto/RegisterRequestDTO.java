package app.mobiletranslate.dto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}
