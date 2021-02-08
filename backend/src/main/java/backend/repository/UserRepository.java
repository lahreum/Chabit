package backend.repository;

import backend.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserEmailAndUserUserPassword(String userEmail, String userPassword);
    Optional<User> findByUserNickname(String nickname);
    List<User> findAll();
    void deleteUser(User user);
    void clearStore();
}