package app.mobiletranslate.dto;

import app.mobiletranslate.entity.Role;
import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private int userId;
    private String username;
    private String email;
    private Role role=Role.USER;
    boolean active;
}
