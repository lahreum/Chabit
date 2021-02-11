package backend.repository;

import backend.domain.badge.Badge;

import java.util.List;

public interface BadgeRepository {
    List<Badge> findByCategoryId(Long categoryId);
    List<Badge> findAll();
}
