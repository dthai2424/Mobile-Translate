package app.mobiletranslate.dto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequestDTO {
    private UserDTO userDTO;
    private String password;
}
