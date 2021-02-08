package backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProofDto {
    private Long proofId;
    private String userEmail;
    private Long challengeId;
    private LocalDateTime proofDate;
    private String proofImage;

    public ProofDto(Proof proof) {
        this.proofId = proof.getProofId();
        this.userEmail = proof.getUser().getUserEmail();
        this.challengeId = proof.getChallenge().getChallengeId();
        this.proofDate = proof.getProofDate();
        this.proofImage = proof.getProofImage();
    }
}
