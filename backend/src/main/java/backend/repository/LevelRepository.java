package backend.repository;

import backend.domain.Level;

import java.util.List;

public interface LevelRepository {
    public Level save(Level level);
    public List<Level> findAll();
}
