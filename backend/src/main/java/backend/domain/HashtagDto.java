package backend.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HashtagDto {
    private List<Hashtag> hashtags = new ArrayList<>();

    public void addHashtag(Hashtag hashtag) {
        this.hashtags.add(hashtag);
    }
}
