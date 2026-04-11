package app.mobiletranslate.controller;

import app.mobiletranslate.dto.LoginRequestDTO;
import app.mobiletranslate.dto.RegisterRequestDTO;
import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.service.UserService;
import app.mobiletranslate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO registerInfo) {
        UserDTO userDTO= userService.register(registerInfo);
        return ResponseEntity.status(201).body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginInfo) {
        UserDTO userDTO= userService.login(loginInfo);

        return ResponseEntity.status(200).body(userDTO);
    }



}
