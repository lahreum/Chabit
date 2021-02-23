//package backend.service;
//
//import backend.domain.user.Level;
//import backend.domain.user.User;
//import backend.repository.LevelRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class LevelServiceTest {
//    @Autowired
//    UserService userService;
//    @Autowired
//    LevelService levelService;
//
//    @Test
//    void save(){
//        Level level = new Level();
//        level.setLevel("100");
//        level.setLevelMinPoint(1000);
//        level.setLevelMaxPoint(1500);
//        levelService.save(level);
//        assertThat(level.getLevelMinPoint()).isEqualTo(1000);
//
//    }
//    @Test
//    void findAll() {
//        Level level3 = new Level();
//        level3.setLevel("300");
//        level3.setLevelMinPoint(1000);
//        level3.setLevelMaxPoint(1500);
//        Level level2 = new Level();
//        level2.setLevel("200");
//        level2.setLevelMinPoint(1501);
//        level2.setLevelMaxPoint(2000);
//        levelService.save(level3);
//        levelService.save(level2);
//        List<Level> result = levelService.findAll();
//        assertThat(result.size()).isEqualTo(2);
//    }
//    @Test
//    void findUserLevel(){
//        User user1 = new User();
//        user1.setUserEmail("corona");
//        user1.setUserPassword("asd");
//        user1.setUserPoints(1010);
//
//        Level level4 = new Level();
//        level4.setLevel("실버");
//        level4.setLevelMinPoint(1000);
//        level4.setLevelMaxPoint(1500);
//        Level level5 = new Level();
//        level5.setLevel("골드");
//        level5.setLevelMinPoint(1501);
//        level5.setLevelMaxPoint(2000);
//        levelService.save(level4);
//        levelService.save(level5);
//        String userLevel = levelService.findUserLevel(user1.getUserPoints());
//        assertThat(userLevel).isEqualTo("실버");
//    }
//}