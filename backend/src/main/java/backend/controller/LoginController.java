package backend.controller;

import backend.domain.User;
import backend.domain.UserDto;
import backend.domain.UserRole;
import backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/v1/login")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @ApiOperation(value="로그인", notes="유저 이메일, 비밀번호 받아 로그인")
    @PostMapping
    public BaseResponse login(@RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            User loginUser = userService.login(request);
            UserDto userDto = new UserDto(loginUser);
            userDto.setUserRole(loginUser.getUserRole().equals(UserRole.ADMIN) ? "ADMIN" : "USER");

            response = new BaseResponse("success", userDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
