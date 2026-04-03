package app.mobiletranslate.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDTO {
    private String username;
    private String password;

}
