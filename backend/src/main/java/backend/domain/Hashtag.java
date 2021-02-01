package backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Hashtag {

    @Id @GeneratedValue
    private Long hashtagId;

    private String hashtagName;
}
