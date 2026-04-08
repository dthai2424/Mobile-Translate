package app.mobiletranslate.service;

import app.mobiletranslate.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllActiveUsers();
    UserDTO getUserById(int userId,boolean active);
    UserDTO getUserByUsername(String username,boolean active);
    UserDTO getUserByEmail(String email,boolean active);
    UserDTO create(UserDTO userDTO,String password);
    UserDTO register(UserDTO userDTO,String password);
    UserDTO login(String username,String password);


}
