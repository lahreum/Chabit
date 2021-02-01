package backend.service;

import backend.domain.Challenge;
import backend.repository.ChallengeRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepoistory challengeRepoistory;


    /**
     * 챌린지 생성.
     * @param challenge
     * @return challengeId
     */
    @Transactional
    public Long makeChallenge(Challenge challenge) throws IllegalStateException{
        challengeRepoistory.save(challenge);
        return challenge.getChallengeId();
    }

    /**
     * 전체 챌린지 조회
     * @return 모든 Challenge List정보
     */
    public List<Challenge> findChallenges(){
        return challengeRepoistory.findAll();
    }
}
