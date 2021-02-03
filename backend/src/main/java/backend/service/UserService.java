package backend.service;

import backend.controller.UserRequest;
import backend.domain.Hashtag;
import backend.domain.User;
import backend.domain.UserHashtag;
import backend.repository.HashtagRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long signIn(User user) throws IllegalStateException {
        //같은 이메일은 중복X
        validateDuplicateUserEmail(user);
        userRepository.save(user);
        return user.getUserId();
    }

    private void validateDuplicateUserEmail(User user) {
        userRepository.findByUserEmail(user.getUserEmail())
            .ifPresent(u -> {
                throw new IllegalStateException("이미 존재하는 이메일 입니다.");
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
}
