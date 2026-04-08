package app.mobiletranslate.dto;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String access_token;

    private UserDTO userDTO;

    @Builder.Default
    private String tokenType = "Bearer";
}
