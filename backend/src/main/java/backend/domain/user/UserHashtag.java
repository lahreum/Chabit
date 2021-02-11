package backend.domain.user;

import backend.domain.hashtag.Hashtag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(UserHashtagId.class)
@Getter @Setter
public class UserHashtag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="HASHTAG_ID")
    private Hashtag hashtag;
}
