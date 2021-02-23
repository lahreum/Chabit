package backend.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ApiModel
public class ProofDto {
    @ApiModelProperty(value = "인증 아이디")
    private Long proofId;
    @ApiModelProperty(value = "인증한 유저")
    private String userEmail;
    @ApiModelProperty(value = "인증한 유저 닉네임")
    private String userNickname;
    @ApiModelProperty(value = "인증한 챌린지")
    private Long challengeId;
    @ApiModelProperty(value = "인증 날짜")
    private LocalDateTime proofDate;
    @ApiModelProperty(value = "인증샷 이미지")
    private String proofImage;

    public ProofDto(Proof proof) {
        this.proofId = proof.getProofId();
        this.userEmail = proof.getUser().getUserEmail();
        this.userNickname = proof.getUser().getUserNickname();
        this.challengeId = proof.getChallenge().getChallengeId();
        this.proofDate = proof.getProofDate();
        this.proofImage = proof.getProofImage();
    }
}
