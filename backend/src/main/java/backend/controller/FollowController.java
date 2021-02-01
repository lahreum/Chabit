package backend.controller;

import backend.domain.Follow;
import backend.exception.DuplicatedFollowingException;
import backend.service.FollowService;
import backend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/follow")
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
            response = new BaseResponse("success", follow.getUserId());
        } catch (DuplicatedFollowingException e) {
            response = new BaseResponse("success", e.getMessage());
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    @Data
    static class FollowRequest {
        private String userEmail;
        private String followingEmail;
    }

}
