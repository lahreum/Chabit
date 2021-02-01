package backend;

import backend.domain.Follow;
import backend.domain.User;
import backend.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class FollowTest {
    @Autowired
    UserService userService;

    @Test
    public void followTest() throws Exception {
        // given
        User user = new User();
        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
        user.setUserPassword("abc");
        Long userId = userService.signIn(user);

        User user2 = new User();
        user2.setUserEmail("asdef@naver.com");// 이메일을 넣었을때
        user2.setUserPassword("abc");
        Long userId2 = userService.signIn(user2);

        // when
        user.follow(user2);

        // then
        System.out.println("----follower list----");
        for (Follow follow : user2.getFollowers()) {
            System.out.println("userEmail = " + follow.getUserId().getUserEmail());
            System.out.println("followingEmail = " + follow.getFollowingId().getUserEmail());
        }

        System.out.println("----following list----");
        for (Follow follow : user.getFollowing()) {
            System.out.println("userEmail = " + follow.getUserId().getUserEmail());
            System.out.println("followingEmail = " + follow.getFollowingId().getUserEmail());
        }
    }
}
