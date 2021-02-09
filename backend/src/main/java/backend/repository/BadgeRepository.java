package backend.repository;

import backend.domain.Badge;

import java.util.List;

public interface BadgeRepository {
    List<Badge> findByCategoryId(Long categoryId);
    List<Badge> findAll();
}
