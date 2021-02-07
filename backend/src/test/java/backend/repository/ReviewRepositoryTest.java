package backend.repository;

import backend.domain.*;
import backend.service.CategoryService;
import backend.service.ChallengeService;
import backend.service.ReviewService;
import backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {

    @Autowired
    UserService userService;
    @Autowired
    ChallengeService challengeService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ReviewService reviewService;
    @Test
    void saveReview() {
        User user = new User();
        user.setUserEmail("asd@naver.com");// 이메일을 넣었을때
        user.setUserPassword("abc");
        Category category = new Category();
        category.setCategoryName("카테고리");
        Challenge challenge =  new Challenge();
        challenge.setChallengeName("챌린지 테스트2");
        challenge.setChallengeDesc("챌린지 설명2");
        challenge.setChallengeOwner(user);
        challenge.setChallengeCategory(category);
        challenge.setChallengeStartdate(LocalDateTime.now());
        challenge.setChallengeEnddate(LocalDateTime.now().plusDays(1));
        challenge.setAuthFrequency("주 3회인증");
        challenge.setAuthHoliday(1);
        userService.signIn(user);
        categoryService.makeCategory(category);
        challengeService.makeChallenge(challenge);
        //
        Review review = new Review();
        review.setCommentCount(100);
        review.setReviewContent("test");
        Review result = reviewService.saveReview(review);
        //
        Assertions.assertEquals(result.getCommentCount(),100);
    }

    @Test
    void findByUserIdOrderByReviewDate() {
        User user3 = new User();
        user3.setUserEmail("asd3@naver.com");// 이메일을 넣었을때
        user3.setUserPassword("abc");
        Category category3 = new Category();
        category3.setCategoryName("카테고리");
        Challenge challenge3 =  new Challenge();
        challenge3.setChallengeName("챌린지 테스트2");
        challenge3.setChallengeDesc("챌린지 설명2");
        challenge3.setChallengeOwner(user3);
        challenge3.setChallengeCategory(category3);
        challenge3.setChallengeStartdate(LocalDateTime.now());
        challenge3.setChallengeEnddate(LocalDateTime.now().plusDays(1));
        challenge3.setAuthFrequency("주 3회인증");
        challenge3.setAuthHoliday(1);
        userService.signIn(user3);
        User userIdTest= userService.findUser("asd3@naver.com");
        categoryService.makeCategory(category3);
        challengeService.makeChallenge(challenge3);
        //
        Review review3 = new Review();
        review3.setUserId(userIdTest);
        review3.setReviewContent("test");
        review3.setReviewDate(LocalDateTime.now());
        reviewService.saveReview(review3);
        Review review4 = new Review();
        review4.setUserId(userIdTest);
        review4.setReviewContent("test2");
        review4.setReviewDate(LocalDateTime.now().plusHours(3));
        reviewService.saveReview(review4); // ㅎㅇㅎㅇ

        List<Review> resultList = reviewService.findByUserIdOrderByReviewDate(userIdTest);

        Assertions.assertEquals(resultList.size(), 2);
    }

    @Test
    void findByReviewId() {
        User user2 = new User();
        user2.setUserEmail("asd@naver.com");// 이메일을 넣었을때
        user2.setUserPassword("abc");
        Category category2 = new Category();
        category2.setCategoryName("카테고리");
        Challenge challenge2 =  new Challenge();
        challenge2.setChallengeName("챌린지 테스트2");
        challenge2.setChallengeDesc("챌린지 설명2");
        challenge2.setChallengeOwner(user2);
        challenge2.setChallengeCategory(category2);
        challenge2.setChallengeStartdate(LocalDateTime.now());
        challenge2.setChallengeEnddate(LocalDateTime.now().plusDays(1));
        challenge2.setAuthFrequency("주 3회인증");
        challenge2.setAuthHoliday(1);
        userService.signIn(user2);
        categoryService.makeCategory(category2);
        challengeService.makeChallenge(challenge2);
        //
        Review review2 = new Review();
        review2.setCommentCount(100);
        review2.setReviewContent("test");
        Review saveTest = reviewService.saveReview(review2);

        Review result =  reviewService.findByReviewId(saveTest.getReviewId());
        Assertions.assertEquals(result.getReviewContent(),"test");

    }

    @Test
    void deleteReview(){
        User user6 = new User();
        user6.setUserEmail("asd@naver.com");// 이메일을 넣었을때
        user6.setUserPassword("abc");
        Category category6 = new Category();
        category6.setCategoryName("카테고리");
        Challenge challenge6 =  new Challenge();
        challenge6.setChallengeName("챌린지 테스트2");
        challenge6.setChallengeDesc("챌린지 설명2");
        challenge6.setChallengeOwner(user6);
        challenge6.setChallengeCategory(category6);
        challenge6.setChallengeStartdate(LocalDateTime.now());
        challenge6.setChallengeEnddate(LocalDateTime.now().plusDays(1));
        challenge6.setAuthFrequency("주 3회인증");
        challenge6.setAuthHoliday(1);
        userService.signIn(user6);
        categoryService.makeCategory(category6);
        challengeService.makeChallenge(challenge6);
        //
        Review review6 = new Review();
        review6.setCommentCount(100);
        review6.setReviewContent("test");
        reviewService.saveReview(review6);
//        Assertions.assertEquals(reviewService.findAll().size(), 1);
        reviewService.deleteReview(review6);
        Assertions.assertEquals(reviewService.findAll().size(), 0);
    }

    //Todo 테스트코드 진행 예정.
    @Test
    void saveReviewImage(){

    }
    @Test
    void findReviewImageByReviewId(){

    }
    @Test
    void deleteReviewImage(){

    }
}