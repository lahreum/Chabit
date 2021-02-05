package backend.controller;

import backend.domain.Follow;
import backend.domain.FollowDto;
import backend.domain.User;
import backend.exception.DuplicatedFollowingException;
import backend.service.FollowService;
import backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/follow")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final UserService userService;

    @PostMapping
    public BaseResponse follow(@RequestBody FollowRequest request) {
        Follow follow = new Follow();
        BaseResponse response = null;
        try {
            follow.setUserId(userService.findUser(request.getUserEmail()));
            follow.setFollowingId(userService.findUser(request.getFollowingEmail()));

            followService.follow(follow);
            response = new BaseResponse("success", follow.getUserId().getUserId());
        } catch (DuplicatedFollowingException e) {
            response = new BaseResponse("success", e.getMessage());
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    @GetMapping("/{userEmail}")
    public BaseResponse findFollwersAndFollowings(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            List<FollowDto> followerList = followService.findByFollowingId(user);
            List<FollowDto> followingList = followService.findByUserId(user);

            FollowerResponse followerResponse = new FollowerResponse(followerList.size(), followerList);
            FollowingResponse followingResponse = new FollowingResponse(followingList.size(), followingList);
            response = new BaseResponse("success",
                    new FollowerAndFollowingResponse(userEmail, followerResponse, followingResponse));
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping
    public BaseResponse unfollow(@RequestBody FollowRequest request) {
        BaseResponse response = null;
        try {
            Follow follow = new Follow();
            follow.setUserId(userService.findUser(request.getUserEmail()));
            follow.setFollowingId(userService.findUser(request.getFollowingEmail()));
            followService.deleteOne(follow);

            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    // ======= Response & Request 클래스 =======
    // 팔로우 요청
    @Data
    static class FollowRequest {
        private String userEmail;
        private String followingEmail;
    }

    // 팔로워, 팔로잉 목록 응답
    @Data
    @AllArgsConstructor
    static class FollowerAndFollowingResponse {
        private String userEmail;
        private FollowerResponse followers;
        private FollowingResponse followings;
    }

    @Data
    @AllArgsConstructor
    static class FollowerResponse {
        private int followerCnt;
        private List<FollowDto> followers;
    }

    @Data
    @AllArgsConstructor
    static class FollowingResponse {
        private int followingCnt;
        private List<FollowDto> followings;
    }
}
