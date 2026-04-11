package app.mobiletranslate.service;

import app.mobiletranslate.dto.LoginRequestDTO;
import app.mobiletranslate.dto.RegisterRequestDTO;
import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllActiveUsers();
    UserDTO getUserById(int userId,boolean active);
    User getUserEntityById(int userId, boolean active);
    UserDTO getUserByUsername(String username,boolean active);
    UserDTO getUserByEmail(String email,boolean active);
    UserDTO create(UserDTO userDTO,String password);
    UserDTO register(RegisterRequestDTO registerRequestDTO);
     UserDTO login(LoginRequestDTO loginRequestDTO);



}
