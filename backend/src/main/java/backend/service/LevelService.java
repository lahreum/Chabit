package backend.service;


import backend.domain.user.Level;
import backend.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;

    @Transactional
    public Level save(Level level){
        return levelRepository.save(level);
    };

    public List<Level> findAll(){
        return levelRepository.findAll();
    }

    public Optional<Level> findOne(String level) {
        return levelRepository.findOne(level);
    }

    public String findUserLevel(int userPoints) {
        List<Level> levelList = levelRepository.findAll();
        if(0 < levelList.size()){
            for (Level level: levelList) {
                if(level.getLevelMinPoint() <= userPoints && userPoints <= level.getLevelMaxPoint()){
                    return level.getLevel();
                }
            }
        }
        return null;
    }
}
