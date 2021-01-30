package backend.service;

import backend.controller.UserRequest;
import backend.domain.User;
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
}
