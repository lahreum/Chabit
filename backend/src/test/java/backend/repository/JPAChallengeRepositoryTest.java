//package backend.repository;
//
//import backend.domain.challenge.Category;
//import backend.domain.challenge.Challenge;
//import backend.domain.user.User;
//import backend.service.CategoryService;
//import backend.service.ChallengeService;
//import backend.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.transaction.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@Transactional
//@WebAppConfiguration
//class JPAChallengeRepositoryTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    ChallengeService challengeService;
//    @Autowired
//    ChallengeRepoistory challengeRepoistory;
//
//    @Test
//    void save() {
//        //given
//
//        User user = new User();
//        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//        userService.signIn(user);
//        Category category = new Category();
//        category.setCategoryName("카테고리");
//        categoryService.makeCategory(category);
//        Challenge challenge =  new Challenge();
//        challenge.setChallengeName("챌린지 테스트");
//        challenge.setChallengeDesc("챌린지 설명");
//        challenge.setChallengeOwner(user);
//        challenge.setChallengeCategory(category);
//        challenge.setChallengeStartdate(LocalDateTime.now());
//        challenge.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge.setAuthFrequency(2);
//        challenge.setAuthHoliday(1);
//        //when
//        challengeService.makeChallenge(challenge);
//        //then
//        org.assertj.core.api.Assertions.assertThat(challenge.getAuthFrequency()).isEqualTo(2);
//    }
//    @Test
//    void findAll(){
//        User user = new User();
//        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//        Category category = new Category();
//        category.setCategoryName("카테고리");
//        Challenge challenge =  new Challenge();
//        challenge.setChallengeName("챌린지 테스트2");
//        challenge.setChallengeDesc("챌린지 설명2");
//        challenge.setChallengeOwner(user);
//        challenge.setChallengeCategory(category);
//        challenge.setChallengeStartdate(LocalDateTime.now());
//        challenge.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge.setAuthFrequency(3);
//        challenge.setAuthHoliday(1);
//        Challenge challenge2 =  new Challenge();
//        User user2 = new User();
//        user2.setUserEmail("asd2@naver.com");// 이메일을 넣었을때
//        user2.setUserPassword("abc2");
//        Category category2 = new Category();
//        category2.setCategoryName("카테고리2");
//        challenge2.setChallengeName("챌린지 테스트");
//        challenge2.setChallengeDesc("챌린지 설명");
//        challenge2.setChallengeOwner(user2);
//        challenge2.setChallengeCategory(category2);
//        challenge2.setChallengeStartdate(LocalDateTime.now());
//        challenge2.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge2.setAuthFrequency(3);
//        challenge2.setAuthHoliday(3);
//        userService.signIn(user);
//        userService.signIn(user2);
//        categoryService.makeCategory(category);
//        categoryService.makeCategory(category2);
//        challengeService.makeChallenge(challenge);
//        challengeService.makeChallenge(challenge2);
//
//        List<Challenge> result = new ArrayList<>();
//        result = challengeService.findChallenges();
//        org.assertj.core.api.Assertions.assertThat(result.size()==2);
//        org.assertj.core.api.Assertions.assertThat(result.get(0).getAuthHoliday() == 1);
//        org.assertj.core.api.Assertions.assertThat(result.get(1).getAuthHoliday() == 3);
//    }
//    @Test
//    void findByChallengeNameLikeContaining(){
//        //given
//        Challenge challenge =  new Challenge();
//        User user = new User();
//        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//        Category category = new Category();
//        category.setCategoryName("카테고리2");
//        challenge.setChallengeName("챌린지 테스트2");
//        challenge.setChallengeDesc("챌린지 설명2");
//        challenge.setChallengeOwner(user);
//        challenge.setChallengeCategory(category);
//        challenge.setChallengeStartdate(LocalDateTime.now());
//        challenge.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge.setAuthFrequency(3);
//        challenge.setAuthHoliday(1);
//        Challenge challenge2 =  new Challenge();
//        User user2 = new User();
//        user2.setUserEmail("asd2@naver.com");// 이메일을 넣었을때
//        user2.setUserPassword("abc2");
//        Category category2 = new Category();
//        category2.setCategoryName("카테고리3");
//        challenge2.setChallengeName("챌린지 테스트");
//        challenge2.setChallengeDesc("챌린지 설명");
//        challenge2.setChallengeOwner(user2);
//        challenge2.setChallengeCategory(category2);
//        challenge2.setChallengeStartdate(LocalDateTime.now());
//        challenge2.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge2.setAuthFrequency(3);
//        challenge2.setAuthHoliday(3);
//        Challenge challenge3 =  new Challenge();
//        User user3 = new User();
//        user3.setUserEmail("asd3@naver.com");// 이메일을 넣었을때
//        user3.setUserPassword("abc3");
//        Category category3 = new Category();
//        category3.setCategoryName("카테고리4");
//        challenge3.setChallengeName("테스트만");
//        challenge3.setChallengeDesc("챌린지 설명");
//        challenge3.setChallengeOwner(user3);
//        challenge3.setChallengeCategory(category3);
//        challenge3.setChallengeStartdate(LocalDateTime.now());
//        challenge3.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge3.setAuthFrequency(3);
//        challenge3.setAuthHoliday(3);
//
//        userService.signIn(user);
//        userService.signIn(user2);
//        userService.signIn(user3);
//        categoryService.makeCategory(category);
//        categoryService.makeCategory(category2);
//        categoryService.makeCategory(category3);
//
//        challengeService.makeChallenge(challenge);
//        challengeService.makeChallenge(challenge2);
//        challengeService.makeChallenge(challenge3);
//        //when
//        List<Challenge> result = new ArrayList<>();
//        result = challengeService.findByChallengeNameLikeContaining("챌린지");
//
//        assertEquals(result.size(),2);
////        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(3);
//    }
//    @Test
//    void findAllOrderByChallengeUserCount() {
//        Challenge challenge =  new Challenge();
//        User user = new User();
//        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
//        user.setUserPassword("abc");
//        Category category = new Category();
//        category.setCategoryName("카테고리2");
//        challenge.setChallengeName("챌린지 테스트2");
//        challenge.setChallengeDesc("챌린지 설명2");
//        challenge.setChallengeOwner(user);
//        challenge.setChallengeCategory(category);
//        challenge.setChallengeUsercount(1000);
//        challenge.setChallengeStartdate(LocalDateTime.now());
//        challenge.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge.setAuthFrequency(3);
//        challenge.setAuthHoliday(1);
//        Challenge challenge2 =  new Challenge();
//        User user2 = new User();
//        user2.setUserEmail("asd3@naver.com");// 이메일을 넣었을때
//        user2.setUserPassword("abc2");
//        Category category2 = new Category();
//        category2.setCategoryName("카테고리3");
//        challenge2.setChallengeName("챌린지 테스트");
//        challenge2.setChallengeDesc("챌린지 설명");
//        challenge2.setChallengeOwner(user);
//        challenge2.setChallengeCategory(category);
//        challenge2.setChallengeUsercount(2000);
//        challenge2.setChallengeStartdate(LocalDateTime.now());
//        challenge2.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge2.setAuthFrequency(3);
//        challenge2.setAuthHoliday(3);
//        Challenge challenge3 =  new Challenge();
//        User user3 = new User();
//        Category category3 = new Category();
//        user3.setUserEmail("asd2@naver.com");// 이메일을 넣었을때
//        user3.setUserPassword("abc2");
//        category3.setCategoryName("카테고리4");
//        challenge3.setChallengeName("테스트만");
//        challenge3.setChallengeDesc("챌린지 설명");
//        challenge3.setChallengeOwner(user3);
//        challenge3.setChallengeCategory(category3);
//        challenge3.setChallengeUsercount(1500);
//        challenge3.setChallengeStartdate(LocalDateTime.now());
//        challenge3.setChallengeEnddate(LocalDateTime.now().plusDays(1));
//        challenge3.setAuthFrequency(3);
//        challenge3.setAuthHoliday(3);
//
//        userService.signIn(user);
//        userService.signIn(user2);
//        userService.signIn(user3);
//        categoryService.makeCategory(category);
//        categoryService.makeCategory(category2);
//        categoryService.makeCategory(category3);
//        challengeService.makeChallenge(challenge);
//        challengeService.makeChallenge(challenge2);
//        challengeService.makeChallenge(challenge3);
//        List<Challenge> result = new ArrayList<>();
//        result = challengeService.findAllOrderByChallengeUserCount();
//
//        org.assertj.core.api.Assertions.assertThat(result.get(0).getChallengeUsercount()).isEqualTo(2000);
//    }
//}
//
