package app.mobiletranslate.dto;

import app.mobiletranslate.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {
    private int userId;
    private String username;
    private String email;
    private Role role=Role.USER;
}
