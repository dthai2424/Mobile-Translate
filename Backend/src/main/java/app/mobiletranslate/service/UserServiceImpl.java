package app.mobiletranslate.service;

import app.mobiletranslate.dto.AuthResponseDTO;
import app.mobiletranslate.dto.LoginRequestDTO;
import app.mobiletranslate.dto.RegisterRequestDTO;
import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.User;
import app.mobiletranslate.exception.AlreadyExistException;
import app.mobiletranslate.exception.NotFoundException;

import app.mobiletranslate.exception.InvalidFormatException;
import app.mobiletranslate.exception.UnauthorizedException;
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
    private JwtService jwtService;
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
      if(user.isEmpty()){
          throw new NotFoundException("Khong tim thay User");
      }else{
          UserDTO userDTO=userUtil.entityToModel(user.get());
          return userDTO;
      }
    }

    @Override
    public User getUserEntityById(int userId, boolean active) {
       Optional<User> user=userRepository.findById(userId);
         if(user.isEmpty())
              throw new NotFoundException("Khong tim thay User");
         return user.get();
    }

    @Override
    public UserDTO getUserByUsername(String username,boolean active) {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new NotFoundException("Khong tim thay User");
        }else{
            UserDTO userDTO=userUtil.entityToModel(user.get());
            return userDTO;
        }
    }

    @Override
    public UserDTO getUserByEmail(String email,boolean active) {
        Optional<User> user=userRepository.findByEmailAndActive(email, active);
        if(user.isEmpty()){
            throw new NotFoundException("Khong tim thay User");
        }else{
            UserDTO userDTO=userUtil.entityToModel(user.get());
            return userDTO;
        }
    }

    @Override
    public UserDTO create(UserDTO userDTO, String password) {
        userDTO.setUsername(userDTO.getUsername().toLowerCase());
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        if(userRepository.existsByUsername(userDTO.getUsername())||userRepository.existsByEmail(userDTO.getEmail())){
            throw new AlreadyExistException("Username hoac email da ton tai");
        }
        if(!userUtil.validateUsername(userDTO.getUsername())) {
            throw new InvalidFormatException("Ten khong hop le");
        }
        if(!userUtil.validateEmail(userDTO.getEmail())) {
            throw new InvalidFormatException("Email khong hop le");
        }
        if(!userUtil.validatePassword(password)){
            throw new InvalidFormatException("Password khong hop le");
        }
        password=passwordEncoder.encode(password);
        User user=userUtil.modelToEntity(userDTO);
        user.setPassword(password);
        user=userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO register(RegisterRequestDTO registerRequestDTO) {
        UserDTO userDTO=registerRequestDTO.getUserDTO();
        String password=registerRequestDTO.getPassword();
        UserDTO newUser=create(userDTO,password);
        return newUser;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String username=loginRequestDTO.getUsername().toLowerCase();
        String password=loginRequestDTO.getPassword();
        Optional<User> user=userRepository.findByUsernameAndActive(username,true);
        if(user.isEmpty()){
            throw new NotFoundException("Khong tim thay User");

        }
        if(!passwordEncoder.matches(password,user.get().getPassword())){
            throw new UnauthorizedException("Tai khoan hoac mat khau khong chinh xac");
        }

        String jwtToken=jwtService.generateToken(user.get());
        UserDTO userDTO=userUtil.entityToModel(user.get());
        AuthResponseDTO response= AuthResponseDTO.builder().userDTO(userDTO).access_token(jwtToken).build();
        return response;
    }
}
