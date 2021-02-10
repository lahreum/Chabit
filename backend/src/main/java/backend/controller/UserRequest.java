package backend.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// 회원 가입 & 회원 수정
@Data
@ApiModel
public class UserRequest {
    @ApiModelProperty(value="사용자 이메일")
    private String userEmail;
    @ApiModelProperty(value="사용자 패스워드")
    private String userPassword;
    @ApiModelProperty(value="사용자 이름")
    private String userName;
    @ApiModelProperty(value="사용자 닉네임")
    private String userNickname;
    @ApiModelProperty(value="사용자 휴대폰 번호")
    private String userPhone;
    @ApiModelProperty(value="사용자 상태 메세지")
    private String userProfileMessage;
}
