package backend.repository;

import backend.domain.user.Proof;
import backend.domain.user.ProofExample;

import java.util.List;
import java.util.Optional;

public interface ProofRepository {
    List<Proof> findAllByChallengeId(Long challengeId);
    Optional<Proof> findByProofId(Long proofId);
    void deleteOne(Proof proof);

    List<ProofExample> findAllProofExample();
}
