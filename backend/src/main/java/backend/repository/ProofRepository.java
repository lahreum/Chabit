package backend.repository;

import backend.domain.user.Proof;

import java.util.List;
import java.util.Optional;

public interface ProofRepository {
    List<Proof> findAllByChallengeId(Long challengeId);
    Optional<Proof> findByProofId(Long proofId);
    void deleteOne(Proof proof);
}
