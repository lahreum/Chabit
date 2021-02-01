package backend.controller;

import lombok.Data;

// 회원 가입 & 회원 수정
@Data
public class UserRequest {
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userPhone;
}
