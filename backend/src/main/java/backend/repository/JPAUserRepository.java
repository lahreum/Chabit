package backend.repository;

import backend.domain.user.User;
import backend.domain.user.UserCategory;
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
    public Optional<User> findByUserNickname(String nickname) {
        List<User> findUser = entityManager.createQuery("select u from User u where u.userNickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return findUser.stream().findAny();
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
    public void updateUserSuccessCount(Long userId, Long categoryId) {
        entityManager.createQuery("update UserCategory uc set uc.successCount = uc.successCount + 1 where uc.category.categoryId = :categoryId and uc.user.userId =: userId" )
                .setParameter("categoryId", categoryId)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public Optional<UserCategory> findSuccessCountByUserAndCategory(Long userId, Long categoryId) {
        List<UserCategory> resultList = entityManager.createQuery("select uc from UserCategory uc where uc.user.userId = :userId and uc.category.categoryId = :categoryId", UserCategory.class)
                .setParameter("userId", userId)
                .setParameter("categoryId", categoryId)
                .getResultList();
        return resultList.stream().findAny();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void clearStore() {

    }

    @Override
    public List<User> findUserContainNickname(String nickname) {
        List<User> users = entityManager.createQuery("select u from User u where u.userNickname like :nickname", User.class)
                .setParameter("nickname", "%" + nickname + "%")
                .getResultList();
        return users;
    }
}
