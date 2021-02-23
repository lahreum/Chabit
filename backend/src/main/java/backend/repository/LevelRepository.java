package backend.repository;

import backend.domain.user.Level;

import java.util.List;
import java.util.Optional;

public interface LevelRepository {
    public Level save(Level level);
    public List<Level> findAll();

    Optional<Level> findOne(String level);
}
