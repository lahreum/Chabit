package backend.service;

import backend.domain.follow.Follow;
import backend.domain.follow.FollowDto;
import backend.domain.user.User;
import backend.exception.DuplicatedFollowingException;
import backend.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 팔로잉 명단
    public List<FollowDto> findByUserId(User user) {
        List<Follow> followingList = followRepository.findByUserId(user);
        // Entity -> DTO
        List<FollowDto> collect = followingList.stream()
                .map(m -> new FollowDto(m.getFollowingId().getUserEmail(), m.getFollowingId().getUserNickname(), m.getFollowingId().getUserImage()))
                .collect(Collectors.toList());
        return collect;
    }

    // 팔로워 명단
    public List<FollowDto> findByFollowingId(User user) {
        List<Follow> followerList = followRepository.findByFollowingId(user);
        List<FollowDto> collect = followerList.stream()
                .map(m -> new FollowDto(m.getUserId().getUserEmail(), m.getUserId().getUserNickname(), m.getUserId().getUserImage()))
                .collect(Collectors.toList());
        return collect;
    }

    // 언팔로우
    @Transactional
    public void deleteOne(Follow follow) {
        Optional<Follow> following = followRepository.findByUserIdAndFollowingId(follow);
        if (following.isPresent()) {
            followRepository.deleteFollow(following.get());
        } else {
            throw new IllegalStateException("이미 처리된 요청입니다.");
        }
    }
}
