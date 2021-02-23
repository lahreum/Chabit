package backend.domain.user;

import backend.domain.challenge.Challenge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter @Setter
@NoArgsConstructor
@ApiModel
public class Proof {
    @Id @GeneratedValue
    private Long proofId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "인증한 유저")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    @ApiModelProperty(value = "인증한 챌린지")
    private Challenge challenge;

    @ApiModelProperty(value = "인증날짜")
    private LocalDateTime proofDate;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty(value = "인증 이미지")
    private String proofImage;

    public static Proof createProof(User user, Challenge challenge, String proofImage) {
        Proof proof = new Proof();
        proof.user = user;
        proof.challenge = challenge;
        proof.proofImage = proofImage;
        proof.proofDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        return proof;
    }
}
