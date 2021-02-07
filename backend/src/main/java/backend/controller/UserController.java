package backend.controller;
//http://localhost:9999/swagger-ui.html
import backend.domain.*;
import backend.service.ChallengeService;
import backend.service.LevelService;
import backend.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LevelService levelService;

    @ApiOperation(value="모든 사용자 조회", notes="모든 사용자 조회")
    @GetMapping
    public BaseResponse users() {
        List<User> findUsers = userService.findUsers();

        // 엔티티 -> DTO 변환
        List<UserDto> collect = findUsers.stream()
                .map(m -> new UserDto(m.getUserEmail(), m.getUserNickname(), m.getUserName(), m.getUserPhone(), m.getUserPoints(), m.getUserJoindate()))
                .collect(Collectors.toList());

        return new BaseResponse("success", collect);
    }

    @ApiOperation(value="닉네임으로 유저 찾기", notes="닉네임으로 유저 찾기")
    @GetMapping("/nickname/{nickname}")
    public BaseResponse chekcNickname(@PathVariable String nickname){
        BaseResponse response = null;
        try {
            User user = userService.findUserByNickname(nickname);
            response = new BaseResponse("success", new UserDto(user));
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value="사용자 한명 조회", notes="사용자 한명 조회")
    @ApiImplicitParam(name = "userEmail", value = "사용자 이메일", required = true)
    @GetMapping("/{userEmail}")
    public BaseResponse user(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            // 유저 찾기
            User findUser = userService.findUser(userEmail);

            // 해당 유저의 해쉬태그 가져오기
            List<UserHashtag> findHashtagList = userService.findHashtag(userEmail);

            // 엔티티 -> DTO
            HashtagDto hashtagDto = new HashtagDto();
            findHashtagList.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));

            UserDto userDto = new UserDto(findUser);
            userDto.addHashtags(hashtagDto);

            // 해당 유저의 레벨 가져오기.
            String userLevel = levelService.findUserLevel(userDto.getUserPoints());
            userDto.addUserLevel(userLevel);

            response = new BaseResponse("success", userDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @PostMapping
    @ApiOperation(value="회원가입", notes="회원가입")
    @ApiImplicitParam(name = "UserRequest", value = "사용자 정보", required = true)
    public BaseResponse signIn(@RequestBody UserRequest request) {
        User user = User.createUser(request);

        BaseResponse response = null;
        try {
            userService.signIn(user);
            response = new BaseResponse("success", new JoinUserResponse("success", "회원가입 성공"));
        } catch (IllegalStateException e) {
            response = new BaseResponse("success", new JoinUserResponse("fail", e.getMessage()));
        }

        return response;
    }

    @PutMapping("/{userEmail}")
    public BaseResponse updateUser(@PathVariable String userEmail, @RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            request.setUserEmail(userEmail);
            userService.updateUser(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/{userEmail}")
    public BaseResponse deleteUser(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            userService.deleteUser(userEmail);
            response = new BaseResponse("success", new DeleteUserResponse("success", "삭제 성공"));
        } catch (IllegalStateException e) {
            response = new BaseResponse("success", new DeleteUserResponse("fail", e.getMessage()));
        }

        return response;
    }

    @PostMapping("/hashtag/{userEmail}")
    public BaseResponse saveHashtag(@PathVariable String userEmail, @RequestBody String hashtagName) {
        BaseResponse response = null;
        try {
            userService.addHashtag(userEmail, hashtagName);
            response = new BaseResponse("success", "추가 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @GetMapping("/hashtag/{userEmail}")
    public BaseResponse getUserHashtag(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            List<UserHashtag> findHashtagList = userService.findHashtag(userEmail);

            // 엔티티 -> DTO
            HashtagDto hashtagDto = new HashtagDto();
            findHashtagList.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));
            response = new BaseResponse("success", hashtagDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/hashtag/{userEmail}/{hashtagName}")
    public BaseResponse deleteHashtag(@PathVariable String userEmail, @PathVariable String hashtagName) {
        BaseResponse response = null;
        try {
            userService.removeHashtag(userEmail, hashtagName);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    // 랭킹
    @GetMapping("/ranking")
    public BaseResponse getRanking(@RequestParam(required = false) String userEmail,
                                   @RequestParam(required = false) Long categoryId,
                                   @RequestParam(required = false, defaultValue = "false") Boolean monthlyRanking) {
        BaseResponse response = null;
        try {
            List<User> ranking = userService.findUserByRankingCondition(userEmail, categoryId, monthlyRanking);
            List<UserDto> collect = ranking.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // ======= Response & Request 클래스 =======
    // 회원 가입
    @Data
    static class JoinUserResponse {
        private String joinResult; // 회원가입 결과
        private String message; // 성공 & 실패 메세지

        public JoinUserResponse() {
        }

        public JoinUserResponse(String joinResult, String message) {
            this.joinResult = joinResult;
            this.message = message;
        }
    }

    // 회원 탈퇴
    @Data
    @AllArgsConstructor
    static class DeleteUserResponse {
        private String deleteResult; // 삭제 결과
        private String message;
    }

}
