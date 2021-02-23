package backend.service;

import backend.domain.badge.Badge;
import backend.domain.badge.BadgeType;
import backend.domain.challenge.*;
import backend.domain.hashtag.Hashtag;
import backend.domain.user.User;
import backend.domain.user.UserBadge;
import backend.domain.user.UserCategory;
import backend.domain.user.UserChallenge;
import backend.exception.NotEnoughPointException;
import backend.repository.BadgeRepository;
import backend.repository.ChallengeRepoistory;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepoistory challengeRepoistory;
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    /**
     * 챌린지 생성.
     */
    @Transactional
    public Challenge makeChallenge(Challenge challenge) throws IllegalStateException{
        challengeRepoistory.save(challenge);

        User owner = challenge.getChallengeOwner();
        owner.changePoint(-challenge.getChallengePoint());
        owner.addHistory(new PointHistory(owner, challenge, LocalDateTime.now(ZoneId.of("Asia/Seoul")), -challenge.getChallengePoint()));
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
    public Long joinChallenge(User user, Challenge challenge) throws NotEnoughPointException {
        // 이미 있는 유저인지 검토
        for(UserChallenge userChallenge : challenge.getChallengers()) {
            if (userChallenge.getUser().getUserId().equals(user.getUserId()))
                return challenge.getChallengeId();
        }
        // 포인트 감소
        user.changePoint(-challenge.getChallengePoint());
        user.addHistory(new PointHistory(user, challenge, LocalDateTime.now(ZoneId.of("Asia/Seoul")), -challenge.getChallengePoint()));

        // 없는 유저면 추가
        challenge.join(new UserChallenge(user, challenge));
        return challenge.getChallengeId();
    }

    @Transactional
    public List<User> endChallenges() {
        List<Challenge> challenges = findChallenges();
        Map<Long, User> winners = new HashMap<>(); // 성공한 사람들
        for (Challenge challenge : challenges) {
            // 챌린지 중 종료 날짜가 어제인 챌린지들만
            if (challenge.getChallengeEnddate().toLocalDate().isEqual(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().minusDays(1))) {
                challenge.setChallengeOngoing(ChallengeOngoing.END);

                // 챌린지 참여 유저들 인증 내역 체크
                List<UserChallenge> challengers = challenge.getChallengers();
                for (UserChallenge u : challengers) {
                    User user = u.getUser();

                    // 유저 인증 개수
                    long count = user.getProofs().stream()
                            .filter(p -> p.getChallenge().getChallengeId().equals(challenge.getChallengeId()))
                            .count();

                    long weeks = Period.between(challenge.getChallengeStartdate().toLocalDate(), challenge.getChallengeEnddate().toLocalDate()).getDays() / 7;
                    long perfectProofCount = weeks * challenge.getAuthFrequency(); // 100% 달성률 위한 인증 횟수

                    double proofRatio = (double) count / perfectProofCount * 100; // 유저 달성률

                    // 참여인원, 달성률에 따른 인센티브
                    double peopleIncentive = getPeopleIncentive(challengers.size());
                    double proofIncentive = getProofIncentive(proofRatio);

                    // 챌린지 성공, 실패 여부 저장
                    if (proofRatio >= 80.0)
                        u.setUserChallengeResult(ChallengeResult.SUCCESS);
                    else
                        u.setUserChallengeResult(ChallengeResult.FAIL);
                    // 포인트 지급
                    int point = (int) (challenge.getChallengePoint() * peopleIncentive * proofIncentive);
                    if(point != 0) {
                        user.changePoint(point);
                        user.addHistory(new PointHistory(user, challenge, LocalDateTime.now(ZoneId.of("Asia/Seoul")), point));
                    }
                    
                    // 성공시 카테고리별 성공 횟수 증가후 뱃지 조건 체크
                    if (u.getUserChallengeResult().equals(ChallengeResult.SUCCESS)) {
                        userRepository.updateUserSuccessCount(user.getUserId(), challenge.getChallengeCategory().getCategoryId());
                        winners.putIfAbsent(user.getUserId(), user);
                    }
                }
            }
        }
        return new ArrayList<>(winners.values());
    }

    // 참여인원에 따른 인센티브 계산
    private double getPeopleIncentive(int userNum) {
        double incentive = 0.0f;
        if(userNum >= 100) {
            incentive = 1.3f;
        } else if (userNum >= 10) {
            incentive = 1.2f;
        } else if (userNum >= 5) {
            incentive = 1.1f;
        } else {
            incentive = 1.0f;
        }
        return incentive;
    }

    // 달성률에 따른 인센티브 계산
    private double getProofIncentive(double proofRatio) {
        double incentive = 0.0f;
        if(proofRatio >= 100.0) {
            incentive = 1.3f;
        } else if (proofRatio >= 90.0) {
            incentive = 1.2f;
        } else if (proofRatio >= 80.0) {
            incentive = 1.1f;
        } else {
            incentive = 1.0f;
        }
        return incentive;
    }

    // 챌린지 성공한 사람들 뱃지 체크
    @Transactional
    public void giveBadges(List<User> winners) {
        for (User user : winners) {
            List<UserCategory> userCategories = user.getSuccessCount();

            for (UserCategory uc : userCategories) {
                Optional<Badge> badge = null;
                System.out.println("success count : " + uc.getSuccessCount());
                switch (uc.getSuccessCount()) {
                    case 3:
                        // 3번 성공한 경우 동뱃지
                        System.out.println("called bronze");
                        badge = badgeRepository.findByCategoryId(uc.getCategory().getCategoryId()).stream()
                                .filter(h -> h.getBadgeType().equals(BadgeType.BRONZE))
                                .findAny();
                        badge.ifPresent(b -> user.addBadge(new UserBadge(user, b)));
                        break;
                    case 6:
                        // 6번 성공한 경우 은뱃지
                        System.out.println("called silver");
                        badge = badgeRepository.findByCategoryId(uc.getCategory().getCategoryId()).stream()
                                .filter(h -> h.getBadgeType().equals(BadgeType.SILVER))
                                .findAny();
                        badge.ifPresent(b -> user.addBadge(new UserBadge(user, b)));
                        break;
                    case 9:
                        // 9번 성공한 경우 금뱃지
                        System.out.println("called gold");
                        badge = badgeRepository.findByCategoryId(uc.getCategory().getCategoryId()).stream()
                                .filter(h -> h.getBadgeType().equals(BadgeType.GOLD))
                                .findAny();
                        badge.ifPresent(b -> user.addBadge(new UserBadge(user, b)));
                        break;
                }
            }
        }
    }

    @Transactional
    public void startChallenges() {
        List<Challenge> challenges = findChallenges();
        for (Challenge challenge : challenges) {
            if (!challenge.getChallengeStartdate().toLocalDate().isAfter(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate()) && challenge.getChallengeOngoing().equals(ChallengeOngoing.READY)){
                challenge.setChallengeOngoing(ChallengeOngoing.ONGOING);
            }
        }
    }
}
