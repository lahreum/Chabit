package backend.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ProofExample {

    @Id @GeneratedValue
    @Column(name = "proof_example_id")
    private Long proofExampleId;

    @Column(name = "proof_example_img", columnDefinition = "TEXT")
    private String proofExampleImg;
}
