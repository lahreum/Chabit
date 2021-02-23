package backend.service;

import backend.domain.user.Proof;
import backend.domain.user.ProofExample;
import backend.repository.ProofRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProofService {
    private final ProofRepository proofRepository;

    public List<Proof> findAllByChallengeId(Long challengeId) {
        List<Proof> proofList = proofRepository.findAllByChallengeId(challengeId);
        return proofList;
    }

    public Proof findByProofId(Long proofId) {
        Optional<Proof> proof = proofRepository.findByProofId(proofId);
        if (proof.isPresent())
            return proof.get();
        throw new IllegalStateException("잘못된 인증 아이디입니다");
    }

    public List<ProofExample> getTodayProofList() {
        return proofRepository.findAllProofExample();
    }

    @Transactional
    public void deleteProof(Long proofId) throws IllegalStateException{
        Proof proof = findByProofId(proofId);
        proofRepository.deleteOne(proof);
    }
}
