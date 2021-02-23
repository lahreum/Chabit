package backend.controller;

import backend.domain.follow.Follow;
import backend.domain.follow.FollowDto;
import backend.domain.user.User;
import backend.exception.DuplicatedFollowingException;
import backend.service.FollowService;
import backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Api
@RestController
@RequestMapping("/v1/follow")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final UserService userService;

    @PostMapping
    @ApiOperation(value="팔로우 신청", notes="userEmail -> followingId로 팔로우")
    public BaseResponse follow(@RequestBody FollowRequest request) {
        Follow follow = new Follow();
        BaseResponse response = null;
        try {
            follow.setUserId(userService.findUser(request.getUserEmail()));
            follow.setFollowingId(userService.findUser(request.getFollowingEmail()));
            follow.setFollowDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

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
    @ApiOperation(value="팔로잉 & 팔로우 목록 조회", notes="팔로잉 팔로워 목록 조회")
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
    @ApiOperation(value="팔로우 취소", notes="userEmail -> followingId 팔로우 취소")
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
    @ApiModel
    static class FollowRequest {
        @ApiModelProperty(value = "현재 유저")
        private String userEmail;
        @ApiModelProperty(value = "팔로잉할 유저")
        private String followingEmail;
    }

    // 팔로워, 팔로잉 목록 응답
    @Data
    @AllArgsConstructor
    @ApiModel
    static class FollowerAndFollowingResponse {
        @ApiModelProperty(value = "현재 유저")
        private String userEmail;
        @ApiModelProperty(value = "팔로워 목록")
        private FollowerResponse followers;
        @ApiModelProperty(value = "팔로잉 목록")
        private FollowingResponse followings;
    }

    @Data
    @AllArgsConstructor
    @ApiModel
    static class FollowerResponse {
        @ApiModelProperty(value = "팔로워 숫자")
        private int followerCnt;
        @ApiModelProperty(value = "팔로워 목록")
        private List<FollowDto> followers;
    }

    @Data
    @AllArgsConstructor
    @ApiModel
    static class FollowingResponse {
        @ApiModelProperty(value = "팔로잉 숫자")
        private int followingCnt;
        @ApiModelProperty(value = "팔로잉 목록")
        private List<FollowDto> followings;
    }
}
