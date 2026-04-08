package app.mobiletranslate.service;

import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.User;
import app.mobiletranslate.exception.UserNotFoundException;
import app.mobiletranslate.exception.UserRegisterException;
import app.mobiletranslate.repository.UserRepository;
import app.mobiletranslate.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUtil userUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> getAllActiveUsers() {
        List<User> users=userRepository.findAllByActive(true);
        List<UserDTO>   userList= new ArrayList<UserDTO>();
        for(User user:users){
            userList.add(userUtil.entityToModel(user));
        }
        return userList;
    }

    @Override
    public UserDTO getUserById(int userId,boolean active) {
      Optional<User> user=userRepository.findById(userId);
      if(!user.isEmpty()){
          throw new UserNotFoundException("Khong tim thay User");
      }else{
          UserDTO userDTO=userUtil.entityToModel(user.get());
          return userDTO;
      }
    }

    @Override
    public UserDTO getUserByUsername(String username,boolean active) {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserNotFoundException("Khong tim thay User");
        }else{
            UserDTO userDTO=userUtil.entityToModel(user.get());
            return userDTO;
        }
    }

    @Override
    public UserDTO getUserByEmail(String email,boolean active) {
        Optional<User> user=userRepository.findByEmailAndActive(email, active);
        if(user.isEmpty()){
            throw new UserNotFoundException("Khong tim thay User");
        }else{
            UserDTO userDTO=userUtil.entityToModel(user.get());
            return userDTO;
        }
    }

    @Override
    public UserDTO create(UserDTO userDTO,String password) {
        userDTO.setUsername(userDTO.getUsername().toLowerCase());
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        if(!userUtil.validateUsername(userDTO.getUsername())) {
            throw new UserRegisterException("Ten khong hop le");
        }
        if(!userUtil.validateEmail(userDTO.getEmail())) {
            throw new UserRegisterException("Email khong hop le");
        }
        if(!userUtil.validatePassword(password)){
            throw new UserRegisterException("Password khong hop le");
        }
        password=passwordEncoder.encode(password);
        User user=userUtil.modelToEntity(userDTO);
        user.setPassword(password);
        user=userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO register(UserDTO userDTO, String password) {
        UserDTO newUser=create(userDTO,password);
        return newUser;
    }

    @Override
    public UserDTO login(String username, String password) {
//        Optional<User> user=userRepository.findByUsernameAndActive(username,true);
//        if(user.isEmpty()){
//            throw new UserNotFoundException("Khong tim thay User");
//        }

        return null;
    }
}
