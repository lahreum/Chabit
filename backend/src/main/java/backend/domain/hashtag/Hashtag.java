package backend.domain.hashtag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter @Setter
public class Hashtag {

    @Id @GeneratedValue
    private Long hashtagId;

    private String hashtagName;

    public Hashtag() {
    }

    public Hashtag(String hashtagName) {
        this.hashtagName = hashtagName;
    }
}
