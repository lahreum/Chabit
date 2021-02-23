package backend.controller;

import backend.domain.hashtag.Hashtag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// 챌린지 생성
@Data
@ApiModel
public class ChallengeRequest {
    @ApiModelProperty(value = "챌린지 이름")
    String challengeName;
    @ApiModelProperty(value = "챌린지 설명")
    String challengeDesc;
    @ApiModelProperty(value = "챌린지 썸네일")
    String challengeThumbnail;
    @ApiModelProperty(value = "챌린지 개설자")
    String userEmail;
    @ApiModelProperty(value = "카테고리 이름")
    String categoryName;
    @ApiModelProperty(value = "인증 방법")
    String authWay;
    @ApiModelProperty(value = "챌린지 인증 예시")
    String authExample;
    @ApiModelProperty(value = "인증 빈도 (주 x회)")
    int authFrequency;
    @ApiModelProperty(value = "공휴일 인증 여부 true / false")
    boolean authHoliday;
    @ApiModelProperty(value = "인증 시작 시간")
    String authStarttime;
    @ApiModelProperty(value = "인증 마감 시간")
    String authEndtime;
    @ApiModelProperty(value = "챌린지 시작일")
    String challengeStartdate;
    @ApiModelProperty(value = "챌린지 종료일")
    String challengeEnddate;
    @ApiModelProperty(value = "챌린지 해시태그 리스트")
    List<Hashtag> hashtags;


}