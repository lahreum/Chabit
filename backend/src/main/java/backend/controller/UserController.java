package backend.controller;

import backend.domain.User;
import backend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public BaseResponse<JoinUserResponse> signIn(@RequestBody JoinUserRequest request) {
        User user = new User();
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(request.getUserPassword());
        user.setUserNickname(request.getUserNickname());
        user.setUserPhone(request.getUserPhone());

        BaseResponse<JoinUserResponse> response = new BaseResponse<>();
        try {
            userService.signIn(user);
            response.setStatus("success");
            response.setData(new JoinUserResponse("success", "회원가입 성공"));
        } catch (IllegalStateException e) {
            response.setStatus("success");
            response.setData(new JoinUserResponse("fail", "이미 존재하는 이메일 입니다."));
        }

        return response;
    }

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
}
