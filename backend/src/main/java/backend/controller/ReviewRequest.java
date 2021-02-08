package backend.controller;

import backend.domain.ReviewImage;
import lombok.Data;

import java.util.List;

@Data
public class ReviewRequest {
    //리뷰아이디, 챌린지 아이디, 리뷰 작성내용
    //Long userId;
    Long challengeId;
    String userEamil;
    String reviewContent;
    List<ReviewImage> reviewImageList;
}
