package backend.repository;

import backend.domain.Badge;
import backend.domain.Category;

import java.util.List;

public interface BadgeRepository {
    List<Badge> findByCategoryId(Long categoryId);
}
