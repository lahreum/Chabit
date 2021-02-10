package backend.controller;

import backend.service.AuthService;
import backend.utils.Validator;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api
@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final Validator validator;

    @GetMapping("/sms")
    public BaseResponse getCertificateNumber(@RequestParam(required = false) String phone) {
        BaseResponse response = null;
        try {
            Random random = new Random();
            String secret = "";

            for (int i = 0; i < 6; ++i) {
                secret += Integer.toString(random.nextInt(10));
            }
            authService.sendSms(validator.phoneValidator(phone), secret);
            response = new BaseResponse("success", new SMSResponse(secret));
        } catch (Exception e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SMSResponse {
        String certificateNum;
    }
}
