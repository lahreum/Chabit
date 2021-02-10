package backend.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {


    public void sendSms(String phone, String secret) throws CoolsmsException{
        String api_key = "NCS2POLGPEZHZHRM";
        String api_secret = "55HLGSR7DCML8AMVOUL8FSPPKY48SEIT";

        Message sms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phone);
        params.put("from", "01084423679");
        params.put("type", "SMS");
        params.put("text", "CHABIT 휴대폰인증 테스트 메시지 : 인증번호는 " + "["+secret+"]" + "입니다.");
        params.put("app_version", "test app 1.2");

        sms.send(params);
    }
}
