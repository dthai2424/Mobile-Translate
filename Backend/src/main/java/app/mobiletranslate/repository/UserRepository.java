package app.mobiletranslate.repository;

import app.mobiletranslate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByIdAndActive(String email, boolean active);
    Optional<User> findUserByEmailAndActive(String email,boolean active);
    List<User> findAllByActive(boolean active);
    Optional<User> findUserByUsername(String username);


}
