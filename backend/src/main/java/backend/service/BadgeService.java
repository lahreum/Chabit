package backend.service;

import backend.domain.badge.Badge;
import backend.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public List<Badge> findAll(){
        return badgeRepository.findAll();
    }
}
