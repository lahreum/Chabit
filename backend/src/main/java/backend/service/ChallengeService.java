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
     */
    @Transactional
    public Challenge makeChallenge(Challenge challenge) throws IllegalStateException{
        challengeRepoistory.save(challenge);
        return challenge;
    }

    /**
     * 전체 챌린지 조회
     * @return 모든 Challenge List정보
     */
    public List<Challenge> findChallenges(){
        return challengeRepoistory.findAll();
    }

    /**
     * 챌린지 이름 검색
     * @return 챌린지 이름이 포함된 List 정보
     */
    public List<Challenge> findByChallengeNameLikeContaining(String challengeName) throws IllegalStateException {
        return challengeRepoistory.findByChallengeNameLikeContaining(challengeName);
    }

    /**
     * HOT 챌린지
     * @return 인원수를 기준으로 정렬한 List 정보
     */
    public List<Challenge> findAllOrderByChallengeUserCount() {
        return challengeRepoistory.findAllOrderByChallengeUserCount();
    }
}
