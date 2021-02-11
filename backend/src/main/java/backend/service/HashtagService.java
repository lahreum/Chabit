package backend.service;

import backend.domain.hashtag.Hashtag;
import backend.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    /**
     * 해쉬태그 ID로 해쉬태그 찾아서 반환
     */
    public Hashtag findByHashtagId(Long hashtagId){
        return hashtagRepository.findByHashtagId(hashtagId);
    }

    /**
     * 해쉬태그 생성
     */
    @Transactional
    public Hashtag makeHashtag(String hashtagName) {
        Optional<Hashtag> findHashtag = hashtagRepository.findByHashtagName(hashtagName);
        // 등록된 해쉬태그인 경우는 그대로 반환
        // 없으면 등록 후 반환
        return findHashtag.orElseGet(() -> hashtagRepository.save(new Hashtag(hashtagName)));
    }
}
