package app.mobiletranslate.service;

import app.mobiletranslate.dto.UserDTO;

public interface JWTService {
    String generateJWT(UserDTO userDTO);

}
