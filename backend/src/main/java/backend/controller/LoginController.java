package backend.controller;

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

    @ApiOperation(value="닉네임으로 유저 찾기", notes="닉네임으로 유저 찾기")
    @PostMapping
    public BaseResponse login(@RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            Long userId = userService.login(request);
            response = new BaseResponse("success", userId);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
