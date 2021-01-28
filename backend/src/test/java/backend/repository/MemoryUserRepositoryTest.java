package backend.repository;

import backend.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryUserRepositoryTest {

    UserRepository repository = new MemoryUserRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        User user = new User();
        user.setUserEmail("asd@naver.com");
        user.setUserPassword("123123");
        user.setUserRole("테스트용아이디");
        user.setUserJoindate(LocalDateTime.now());
        user.setUserPoints(1000);

        repository.save(user);

        User result = repository.findByUserId(user.getUserId()).get();
        assertEquals(user, result);// save 테스트 성공.
    }

    @Test
    public void findByUserEmailAndUserUserPassword(){
        User user1 = new User();
        user1.setUserEmail("asd@naver.com");
        user1.setUserPassword("123123");
        user1.setUserRole("테스트용아이디");
        user1.setUserJoindate(LocalDateTime.now());
        user1.setUserPoints(1000);

        repository.save(user1);

        User user2 = new User();
        user2.setUserEmail("qwe@naver.com");
        user2.setUserPassword("123qwe123");
        user2.setUserRole("테스트용아이디2");
        user2.setUserJoindate(LocalDateTime.now());
        user2.setUserPoints(1000);

        repository.save(user2);

        User result = repository.findByUserEmailAndUserUserPassword("asd@naver.com","123123").get();

        assertEquals(result,user1);
    }
    @Test
    public void findAll(){
        User user1 = new User();
        user1.setUserEmail("asd@naver.com");
        user1.setUserPassword("123123");
        user1.setUserRole("테스트용아이디");
        user1.setUserJoindate(LocalDateTime.now());
        user1.setUserPoints(1000);

        repository.save(user1);

        User user2 = new User();
        user2.setUserEmail("qwe@naver.com");
        user2.setUserPassword("123qwe123");
        user2.setUserRole("테스트용아이디2");
        user2.setUserJoindate(LocalDateTime.now());
        user2.setUserPoints(1000);

        repository.save(user2);

        List<User> result =repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

