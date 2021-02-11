package backend.domain.hashtag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel
public class HashtagDto {
    @ApiModelProperty(value = "해쉬태그 목록")
    private List<Hashtag> hashtags = new ArrayList<>();

    public void addHashtag(Hashtag hashtag) {
        this.hashtags.add(hashtag);
    }
}
