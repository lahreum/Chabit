package backend.repository;

import backend.domain.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepoistory{
    Challenge save(Challenge challenge);
    
    List<Challenge> findAll();
}
