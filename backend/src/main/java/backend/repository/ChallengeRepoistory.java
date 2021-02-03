package backend.repository;

import backend.domain.Challenge;
import backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepoistory{
    Challenge save(Challenge challenge);
    List<Challenge> findByChallengeNameLikeContaining(String challengeName);
    List<Challenge> findAllOrderByChallengeUserCount();
    List<Challenge> findAll();
}
