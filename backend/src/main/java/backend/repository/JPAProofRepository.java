package backend.repository;

import backend.domain.user.Proof;
import backend.domain.user.ProofExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPAProofRepository implements ProofRepository {
    private final EntityManager entityManager;

    @Override
    public List<Proof> findAllByChallengeId(Long challengeId) {
        List<Proof> proofList = entityManager.createQuery("select p from Proof p where p.challenge.challengeId = :challengeId", Proof.class)
                .setParameter("challengeId", challengeId)
                .getResultList();
        return proofList;
    }

    @Override
    public Optional<Proof> findByProofId(Long proofId) {
        Proof proof = entityManager.find(Proof.class, proofId);
        return Optional.ofNullable(proof);
    }

    @Override
    public void deleteOne(Proof proof) {
        entityManager.remove(proof);
    }

    @Override
    public List<ProofExample> findAllProofExample() {
        List<ProofExample> proofExampleList = entityManager.createQuery("select pe from ProofExample pe", ProofExample.class)
                .getResultList();
        return proofExampleList;
    }

}
