package backend.service;


import backend.domain.Level;
import backend.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}
