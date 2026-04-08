package app.mobiletranslate.service;

import app.mobiletranslate.entity.User;
import app.mobiletranslate.exception.UserNotFoundException;
import app.mobiletranslate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UserNotFoundException("Khong tim thay user voi username nay!");
        else return user.get();
    }
}
