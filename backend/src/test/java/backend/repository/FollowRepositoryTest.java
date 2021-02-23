//package backend.repository;
//
//import backend.domain.follow.Follow;
//import backend.domain.user.User;
//import backend.service.FollowService;
//import backend.service.UserService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class FollowRepositoryTest {
//    @Autowired
//    UserService userService;
//    @Autowired
//    FollowService followService;
//
//    @Test
//    public void follow() throws Exception {
//        // given
//        // 유저1 -> 유저2 팔로잉
//        // 유저 1
//        User user = new User();
//        user.setUserEmail("follow@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//
//        // 유저2
//        User user2 = new User();
//        user2.setUserEmail("following@naver.com");// 이메일을 넣었을때
//        user2.setUserPassword("abc");
//
//        userService.signIn(user);
//        userService.signIn(user2);
//
//        // 팔로잉
//        Follow following = new Follow();
//        following.setUserId(user);
//        following.setFollowingId(user2);
//
//        // when
//        Follow follow = followService.follow(following);
//
//        // then
//        System.out.println("follow_userid : " + follow.getUserId().getUserEmail());
//        System.out.println("follow_followingId : " + follow.getFollowingId().getUserEmail());
//        Assertions.assertThat(follow.getUserId()).isEqualTo(user);
//        Assertions.assertThat(follow.getFollowingId()).isEqualTo(user2);
//    }
//}