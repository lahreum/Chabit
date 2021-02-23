//package backend.service;
//
//import backend.domain.user.User;
//import backend.repository.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//@SpringBootTest
//@Transactional
//class UserServiceIntegrationTest {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    void signIn() {
//        //given
//        User user = new User();
//        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//
//        //when
//        Long userId = userService.signIn(user);
//
//        //then
//        Assertions.assertThat(user.getUserEmail()).isEqualTo("asd@naver.com");
//    }
//
//    @Test
//    public void 중복이메일예외(){
//        //given
//        User user1 = new User();
//        user1.setUserEmail("corona");
//        user1.setUserPassword("asd");
//
//        User user2 = new User();
//        user2.setUserEmail("corona");
//        user2.setUserPassword("asd");
//
//        //when
//        userService.signIn(user1);
//
//        //then
//        try {
//            userService.signIn(user2);
//            fail();
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일 입니다.");
//        }
//    }
//
//    @Test
//    void findUsers() {
//
//    }
//}