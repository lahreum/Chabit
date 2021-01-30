package backend.service;

import backend.domain.User;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
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


}
