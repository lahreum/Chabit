package backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Proof {
    @Id @GeneratedValue
    private Long proofId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private LocalDateTime proofDate;
    @Column(columnDefinition = "TEXT")
    private String proofImage;

    public static Proof createProof(User user, Challenge challenge, String proofImage) {
        Proof proof = new Proof();
        proof.user = user;
        proof.challenge = challenge;
        proof.proofImage = proofImage;
        proof.proofDate = LocalDateTime.now();

        return proof;
    }
}
