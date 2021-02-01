package backend.service;

import backend.domain.Follow;
import backend.exception.DuplicatedFollowingException;
import backend.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    /**
     * 팔로잉
     */
    @Transactional
    public Follow follow(Follow follow) {
        validateDuplicateFollowing(follow);
        return followRepository.follow(follow);
    }

    private void validateDuplicateFollowing(Follow follow) {
        followRepository.findByUserIdAndFollowingId(follow)
                .ifPresent(u -> {
                    throw new DuplicatedFollowingException("이미 팔로잉 중인 유저입니다");
                });
    }
}
