package backend.controller;

import backend.domain.ReviewImage;
import lombok.Data;

import java.util.List;

@Data
public class ReviewRequest {
    //리뷰아이디, 챌린지 아이디, 리뷰 작성내용
    Long challengeId;
    String userEmail;
    String reviewContent;
    List<String> reviewImageList;
}
