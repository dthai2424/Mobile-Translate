package app.mobiletranslate.repository;

import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserIdAndActive(String email, boolean active);
    Optional<User> findByEmailAndActive(String email,boolean active);
    List<User> findAllByActive(boolean active);

    Optional<User> findByUsernameAndActive(String username,boolean active);
    Optional<User> findByUsernameAndPasswordAndActive(String username,String password,boolean active);
    Optional<User> findByUsername(String username);
}
