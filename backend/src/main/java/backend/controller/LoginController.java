package backend.controller;

import backend.domain.user.*;
import backend.service.LevelService;
import backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api
@RestController
@RequestMapping("/v1/login")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final LevelService levelService;

    @ApiOperation(value="로그인", notes="유저 이메일, 비밀번호 받아 로그인")
    @PostMapping
    public BaseResponse login(@RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            User loginUser = userService.login(request);
            UserDto userDto = new UserDto(loginUser);
            userDto.setUserPassword(loginUser.getUserPassword());
            userDto.setUserRole(loginUser.getUserRole().equals(UserRole.ADMIN) ? "ADMIN" : "USER");

            // 유저 레벨 담아서 보내기
            String userLevel = levelService.findUserLevel(loginUser.getUserPoints());
            Optional<Level> findLevel = levelService.findOne(userLevel);
            findLevel.ifPresent(level -> userDto.addUserLevel(new LevelDto(level)));

            response = new BaseResponse("success", userDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
