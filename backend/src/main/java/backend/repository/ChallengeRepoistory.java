package backend.repository;

import backend.domain.challenge.Challenge;

import java.util.List;

public interface ChallengeRepoistory{
    Challenge save(Challenge challenge);
    List<Challenge> findByChallengeNameLikeContaining(String challengeName);
    List<Challenge> findAllOrderByChallengeUserCount();
    List<Challenge> findAll();
    Challenge findByChallengeId(Long challengeId);
}
