package backend.service;

import backend.controller.UserRequest;
import backend.domain.challenge.Category;
import backend.domain.challenge.Challenge;
import backend.domain.challenge.PointHistory;
import backend.domain.follow.Follow;
import backend.domain.hashtag.Hashtag;
import backend.domain.user.*;
import backend.repository.CategoryRepository;
import backend.repository.FollowRepository;
import backend.repository.HashtagRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final FollowRepository followRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long signIn(User user) throws IllegalStateException {
        //같은 이메일은 중복X
        validateDuplicateUserEmail(user);
        validateDuplicateUserNickname(user);
        userRepository.save(user);
        return user.getUserId();
    }

    public User login(UserRequest request) throws IllegalStateException {
        // 이메일 확인
        User user = findUser(request.getUserEmail());
        // 비밀번호 확인
        if (user.getUserPassword().equals(request.getUserPassword()))
            return user;
        throw new IllegalStateException("잘못된 비밀번호입니다");
    }

    private void validateDuplicateUserEmail(User user) {
        userRepository.findByUserEmail(user.getUserEmail())
            .ifPresent(u -> {
                throw new IllegalStateException("이미 존재하는 이메일 입니다.");
            });
    }

    private void validateDuplicateUserNickname(User user) {
        userRepository.findByUserNickname(user.getUserNickname())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    /**
     * 유저아이디로 조회
     */
    public Optional<User> findUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * 회원 1명 조회
     */
    public User findUser(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("잘못된 유저 이메일입니다"));
    }

    /**
     * 닉네임으로 유저 조회
     */
    public User findUserByNickname(String nickname){
        Optional<User> user = userRepository.findByUserNickname(nickname);
        if(user.isPresent())
            return user.get();
        else
            throw new IllegalStateException("없는 유저입니다");
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteUser(String userEmail) {
        Optional<User> deleteUser = userRepository.findByUserEmail(userEmail);
        if (deleteUser.isPresent()) {
            userRepository.deleteUser(deleteUser.get());
        } else {
            throw new IllegalStateException("이미 삭제된 유저입니다");
        }
    }

    /**
     * 회원 정보 수정
     * 이메일, 비밀번호, 닉네임
     */
    @Transactional
    public void updateUser(UserRequest request) {
        Optional<User> findUser = userRepository.findByUserEmail(request.getUserEmail());

        if (findUser.isPresent()) {
            User updateUser = findUser.get();
            updateUser.setUserPassword(request.getUserPassword());
            updateUser.setUserNickname(request.getUserNickname());
            updateUser.setUserName(request.getUserName());
            updateUser.setUserPhone(request.getUserPhone());
        } else {
            throw new IllegalStateException("잘못된 유저 이메일입니다");
        }
    }

    /**
     * 상태메세지 추가
     */
    @Transactional
    public void putProfileMessage(User user, String userProfileMessage) {
        user.putProfileMessage(userProfileMessage);
    }

    /**
     * 해쉬태그 추가
     */
    @Transactional
    public void addHashtag(String userEmail, String hashtagName) {
        // 유저 검색
        Optional<User> findUser = userRepository.findByUserEmail(userEmail);
        if(!findUser.isPresent())
            throw new IllegalStateException("잘못된 유저 이메일입니다.");
        User user = findUser.get();

        // 해쉬태그 검색
        Optional<Hashtag> hashtag = hashtagRepository.findByHashtagName(hashtagName);
        UserHashtag userHashtag = new UserHashtag();

        if (hashtag.isPresent()) {
            // 해쉬태그 이미 존재하는 경우
            List<UserHashtag> userHashtagList = user.getHashtags();
            for (UserHashtag tag : userHashtagList) {
                // 유저가 이미 등록한 해쉬태그라면 무시
                if (tag.getHashtag().getHashtagName().equals(hashtagName))
                    return;
            }
            // 유저가 등록 안한 해쉬태그라면 추가
            userHashtag.setUser(user);
            userHashtag.setHashtag(hashtag.get());

            user.addHashtag(userHashtag);
        } else {
            // 해쉬태그 존재하지 않는 경우 등록 후 저장
            userHashtag = new UserHashtag();
            userHashtag.setUser(user);
            userHashtag.setHashtag(hashtagRepository.save(new Hashtag(hashtagName)));

            user.addHashtag(userHashtag);
        }
    }

    /**
     * 유저 해쉬태그 목록 가져오기
     */
    public List<UserHashtag> findHashtag(String userEmail) {
        // 유저 검색
        Optional<User> findUser = userRepository.findByUserEmail(userEmail);
        if(!findUser.isPresent())
            throw new IllegalStateException("잘못된 유저 이메일입니다.");
        User user = findUser.get();

        return user.getHashtags();
    }

    /**
     * 유저 해쉬태그 삭제
     */
    @Transactional
    public void removeHashtag(String userEmail, String hashtagName) {
        User user = findUser(userEmail);
        Optional<Hashtag> findHashtag = hashtagRepository.findByHashtagName(hashtagName);

        if (findHashtag.isPresent()) {
            Hashtag target = findHashtag.get();
            user.getHashtags().removeIf(h -> h.getHashtag().equals(target));
        } else {
            throw new IllegalStateException("잘못된 해쉬태그 이름입니다.");
        }
    }

    /**
     * 조건에 따라 랭킹 조회
     *
     * userEmail : 현재 유저
     * categoryId : 조회하려는 카테고리
     * monthlyRanking : true면 이번달 랭킹만
     * onlyFollowing : true면 팔로잉하는 유저 랭킹만
     */
    public List<UserDto> findUserByRankingCondition(String userEmail, Long categoryId, boolean monthlyRanking, boolean onlyFollowing){
        List<User> userList = new ArrayList<>();
        List<UserDto> result = new ArrayList<>();
        // 팔로잉 유저 선택하면 팔로잉하는 유저 목록만 가져옴
        // 아니면 전체 유저 목록 포인트로 정렬해서 가져옴
        if(onlyFollowing) {
            User user = findUser(userEmail);
            List<Follow> following = followRepository.findByUserId(user);
            // following 하는 유저들 뽑아서 넣음
            for(Follow follow : following) {
                userList.add(follow.getFollowingId());
            }
        } else {
            userList = userRepository.findAll();
        }

        Optional<Category> findCategory = categoryRepository.findByCategoryId(categoryId);
        // 카테고리별 정렬 선택한 경우
        if(findCategory.isPresent()) {
            // 도전한 챌린지를 카테고리로 필터링 후 해당 챌린지들에서 잃고 얻은 포인트 합계를 통해 정렬
            Category category = findCategory.get();
            final Comparator<User> comp = (u1, u2) ->
                    Integer.compare(u1.getPointHistories().stream()
                                    .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                                    .filter(h -> h.getChallenge().getChallengeCategory().equals(category))
                                    .mapToInt(PointHistory::getPointChange)
                                    .sum(),
                            u2.getPointHistories().stream()
                                    .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                                    .filter(h -> h.getChallenge().getChallengeCategory().equals(category))
                                    .mapToInt(PointHistory::getPointChange)
                                    .sum());

            result = userList.stream()
                    .filter(u ->
                            u.getPointHistories().stream()
                                    .anyMatch(h -> h.getChallenge().getChallengeCategory().equals(category)))
                    .sorted(comp.reversed())
                    .map(u -> new UserDto(u,
                            u.getPointHistories().stream()
                            .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                            .filter(h -> h.getChallenge().getChallengeCategory().equals(category))
                            .mapToInt(PointHistory::getPointChange)
                            .sum()))
                    .collect(Collectors.toList());
        } else if(monthlyRanking) {
            // 카테고리별 정렬 선택 안한 경우
            final Comparator<User> comp = (u1, u2) ->
                    Integer.compare(
                            u1.getPointHistories().stream()
                                    .filter(h -> h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                                    .mapToInt(PointHistory::getPointChange)
                                    .sum(),
                            u2.getPointHistories().stream()
                                    .filter(h -> h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                                    .mapToInt(PointHistory::getPointChange)
                                    .sum()
                    );
            result = userList.stream()
                    .filter(u ->
                            u.getPointHistories().stream()
                                    .anyMatch(h -> h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth())))
                    .sorted(comp.reversed())
                    .map(u -> new UserDto(u,
                            u.getPointHistories().stream()
                            .filter(h -> h.getPointDate().getMonth().equals(LocalDate.now(ZoneId.of("Asia/Seoul")).getMonth()))
                            .mapToInt(PointHistory::getPointChange)
                            .sum()))
                    .collect(Collectors.toList());
        } else {
            // 카테고리별 정렬도 아니고 월간 정렬도 아닌 경우 유저가 가진 포인트 그대로 담아서 보냄
            result = userList.stream()
                    .sorted((u1, u2) -> Integer.compare(u2.getUserPoints(), u1.getUserPoints()))
                    .map(u -> new UserDto(u, u.getUserPoints()))
                    .collect(Collectors.toList());
        }

        return result;
    }

    // 챌린지 인증
    @Transactional
    public void proofChallenge(User user, Challenge challenge, String proofUrl) {
        Proof proof = Proof.createProof(user, challenge, proofUrl);
        user.proofChallenge(proof);
    }

    // 유저 프로필사진 저장
    @Transactional
    public void putUserImage(User user, String userImage) {
        user.setUserImage(userImage);
    }

    // 카테고리별로 유저가 챌린지 성공한 횟수 초기화
    @Transactional
    public void initSuccessCount(User user) {
        List<Category> categories = categoryRepository.findAll();

        for (Category c : categories) {
            user.addSuccessCount(new UserCategory(user, c, 0));
        }
    }

    public List<User> findUserContainNickname(String nickname) {
        return userRepository.findUserContainNickname(nickname);
    }
}
