package app.mobiletranslate.util;

import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.User;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Builder
@NoArgsConstructor
public class UserUtil {
    public UserDTO entityToModel(User user){
        UserDTO userDTO=UserDTO.builder().userId(user.getUserId()).username(user.getUsername()).email(user.getEmail()).role(user.getRole()).active(user.isActive()).build();
        return userDTO;
    }
    public User modelToEntity(UserDTO userDTO){
        User user= User.builder().username(userDTO.getUsername()).email(userDTO.getEmail()).role(userDTO.getRole()).active(userDTO.isActive()).build();
        return user;
    }
    public boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    public boolean validateUsername(String username){
        String usernameRegex="^[a-zA-Z0-9._-]+$";
        return username.matches(usernameRegex);
    }
    public boolean validatePassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";

        return password.matches(passwordRegex);
    }

}
