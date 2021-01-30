package backend.controller;

import backend.domain.User;
import backend.domain.UserDto;
import backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public BaseResponse users() {
        List<User> findUsers = userService.findUsers();

        // 엔티티 -> DTO 변환
        List<UserDto> collect = findUsers.stream()
                .map(m -> new UserDto(m.getUserEmail(), m.getUserNickname(), m.getUserName(), m.getUserPhone()))
                .collect(Collectors.toList());

        return new BaseResponse("success", collect);
    }

    @PostMapping
    public BaseResponse signIn(@RequestBody JoinUserRequest request) {
        User user = new User();
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(request.getUserPassword());
        user.setUserNickname(request.getUserNickname());
        user.setUserPhone(request.getUserPhone());

        BaseResponse response = null;
        try {
            userService.signIn(user);
            response = new BaseResponse("success", new JoinUserResponse("success", "회원가입 성공"));
        } catch (IllegalStateException e) {
            response = new BaseResponse("success", new JoinUserResponse("fail", e.getMessage()));
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

    // ======= Response & Request 클래스 =======
    // 회원 가입
    @Data
    static class JoinUserResponse {
        private String joinResult; // 회원가입 결과
        private String message; // 성공 & 실패 메세지

        public JoinUserResponse(){}

        public JoinUserResponse(String joinResult, String message) {
            this.joinResult = joinResult;
            this.message = message;
        }
    }
    
    @Data
    static class JoinUserRequest {
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userPhone;
    }
    // 회원 탈퇴
    @Data
    @AllArgsConstructor
    static class DeleteUserResponse {
        private String deleteResult; // 삭제 결과
        private String message;
    }
}
