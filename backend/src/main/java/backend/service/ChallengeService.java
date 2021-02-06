package backend.service;

import backend.domain.*;
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
     * 챌린지 1개 조회
     */
    public Challenge findByChallengeId(Long challengeId){
        return challengeRepoistory.findByChallengeId(challengeId);
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

    /**
     * 챌린지에 해쉬태그 추가
     */
    @Transactional
    public Long addHashtag(Challenge challenge, Hashtag hashtag) {
        challenge.addHashtag(new ChallengeHashtag(challenge, hashtag));
        return challenge.getChallengeId();
    }

    /**
     * 챌린지 참가
     */
    @Transactional
    public Long joinChallenge(User user, Challenge challenge){
        // 이미 있는 유저인지 검토
        for(UserChallenge userChallenge : challenge.getChallengers()) {
            if (userChallenge.getUser().getUserId().equals(user.getUserId()))
                return challenge.getChallengeId();
        }
        // 없으면 추가
        challenge.join(new UserChallenge(user, challenge));
        return challenge.getChallengeId();
    }
}
