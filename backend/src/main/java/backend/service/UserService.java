package backend.service;

import backend.controller.UserRequest;
import backend.domain.*;
import backend.repository.CategoryRepository;
import backend.repository.FollowRepository;
import backend.repository.HashtagRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
     * 조건에 따라 챌린지 조회
     */
    public List<User> findUserByRankingCondition(String userEmail, Long categoryId, boolean monthlyRanking){
        List<User> result = new ArrayList<>();

        // 팔로잉 유저 선택하면 팔로잉하는 유저 목록만 가져옴
        // 아니면 전체 유저 목록 포인트로 정렬해서 가져옴
        if(userEmail != null) {
            User user = findUser(userEmail);
            List<Follow> following = followRepository.findByUserId(user);
            // following 하는 유저들 뽑아서 넣음
            for(Follow follow : following) {
                result.add(follow.getFollowingId());
            }
        } else {
            result = userRepository.findAll();
        }

        // 카테고리별 정렬 선택한 경우
        if(categoryId != null) {
            Optional<Category> findCategory = categoryRepository.findByCategoryId(categoryId);
            if(!findCategory.isPresent())
                throw new IllegalStateException("잘못된 카테고리입니다");
            // 도전한 챌린지를 카테고리로 필터링 후 해당 챌린지들에서 잃고 얻은 포인트 합계를 통해 정렬
            Category category = findCategory.get();
            final Comparator<User> comp = (u1, u2) ->
                Integer.compare(u1.getPointHistories().stream()
                    .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now().getMonth()))
                    .filter(h -> h.getChallenge().getChallengeCategory().equals(category))
                    .mapToInt(PointHistory::getPointChange)
                    .sum(),
                    u2.getPointHistories().stream()
                    .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now().getMonth()))
                    .filter(h -> h.getChallenge().getChallengeCategory().equals(category))
                    .mapToInt(PointHistory::getPointChange)
                    .sum());

            result = result.stream()
                    .filter(u ->
                            u.getPointHistories().stream()
                                    .anyMatch(h -> h.getChallenge().getChallengeCategory().equals(category)))
                    .sorted(comp.reversed())
                    .collect(Collectors.toList());
        } else {
            // 카테고리별 정렬 선택 안한 경우
            final Comparator<User> comp = (u1, u2) ->
                    Integer.compare(
                            u1.getPointHistories().stream()
                                .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now().getMonth()))
                                .mapToInt(PointHistory::getPointChange)
                                .sum(),
                            u1.getPointHistories().stream()
                                .filter(h -> !monthlyRanking || h.getPointDate().getMonth().equals(LocalDate.now().getMonth()))
                                .mapToInt(PointHistory::getPointChange)
                                .sum()
                    );
            result = result.stream()
                    .filter(u ->
                            !monthlyRanking || u.getPointHistories().stream()
                                    .anyMatch(h -> h.getPointDate().getMonth().equals(LocalDate.now().getMonth())))
                    .sorted(comp.reversed())
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
}
