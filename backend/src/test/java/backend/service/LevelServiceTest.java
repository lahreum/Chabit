package backend.service;

import backend.domain.Level;
import backend.repository.LevelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LevelServiceTest {
    @Autowired
    LevelService levelService;

    @Test
    void save(){
        Level level = new Level();
        level.setLevel("100");
        level.setLevelMinPoint(1000);
        level.setLevelMaxPoint(1500);
        levelService.save(level);
        assertThat(level.getLevelMinPoint()).isEqualTo(1000);

    }
    @Test
    void findAll() {
        Level level3 = new Level();
        level3.setLevel("300");
        level3.setLevelMinPoint(1000);
        level3.setLevelMaxPoint(1500);
        Level level2 = new Level();
        level2.setLevel("200");
        level2.setLevelMinPoint(1501);
        level2.setLevelMaxPoint(2000);
        levelService.save(level3);
        levelService.save(level2);
        List<Level> result = levelService.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}