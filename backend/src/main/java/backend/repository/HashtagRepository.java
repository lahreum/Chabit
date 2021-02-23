package backend.repository;

import backend.domain.hashtag.Hashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HashtagRepository {
    private final EntityManager entityManager;

    public List<Hashtag> findAll() {
        List<Hashtag> hashtagList = entityManager.createQuery("select h from Hashtag h", Hashtag.class)
                .getResultList();
        return hashtagList;
    }

    public Optional<Hashtag> findByHashtagName(String hashtagName) {
        List<Hashtag> hashtagList = entityManager.createQuery("select h from Hashtag h where h.hashtagName = :hashtagName", Hashtag.class)
                .setParameter("hashtagName", hashtagName)
                .getResultList();

        return hashtagList.stream().findAny();
    }

    public Hashtag save(Hashtag hashtag) {
        entityManager.persist(hashtag);
        return hashtag;
    }

    public Hashtag findByHashtagId(Long hashtagId) {
        Hashtag findHashtag = entityManager.find(Hashtag.class, hashtagId);
        return findHashtag;
    }
}
