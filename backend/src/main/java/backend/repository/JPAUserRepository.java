package backend.repository;

import backend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPAUserRepository implements UserRepository{

    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(Long userId) {
        User user = entityManager.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUserEmail(String userEmail) {
        List<User> result = entityManager.createQuery("select u from User u where u.userEmail =: userEmail", User.class)
                                        .setParameter("userEmail", userEmail)
                                        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUserEmailAndUserUserPassword(String userEmail, String userPassword) {
        List<User> result = entityManager.createQuery("select u from User u where u.userEmail =:userEmail and u.userPassword =: userPassword", User.class)
                                        .setParameter("userEmail", userEmail)
                                        .setParameter("userPassword", userPassword)
                                        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> result = entityManager.createQuery("select u from User u", User.class)
                                        .getResultList();
        return result;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void clearStore() {

    }
}
