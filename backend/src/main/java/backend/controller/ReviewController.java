package backend.controller;

import backend.domain.*;
import backend.service.ChallengeService;
import backend.service.ReviewService;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/review")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ReviewController {
    private final UserService userService;
    private final ChallengeService challengeService;
    private final ReviewService reviewService;

    /**
     * 리뷰 생성
     * @param request : 리뷰 컨텐트, 유저 이메일정보와 챌린지 Id정보
     * @return
     */
    @PostMapping
    public BaseResponse makeReview(@RequestBody ReviewRequest request){
        BaseResponse response = null;
        try {
            User user = userService.findUser(request.getUserEamil());
            Challenge challenge = challengeService.findByChallengeId(request.getChallengeId());

            Review newReview = Review.createReview(user,challenge, request.getReviewContent());
            Review saveReview = reviewService.saveReview(newReview);//저장

            for(ReviewImage reviewImage : request.getReviewImageList()){
                reviewImage.setReviewId(newReview);//리뷰의id값도 넣어주자.
                reviewService.saveReviewImage(reviewImage); // 리뷰 이미지 하나씩 저장하자.
            }
            response = new BaseResponse("success","리뷰 저장 성공");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    ////////////////// 이미지 받는 기능 추후 수정할 것.

    /**
     * 해당 유저 마이리뷰 목록 불러오기
     * 해당 유저 email을 받아오면
     * @param userEmail
     * @return 해당유저 review의 List(최신 순으로 정렬)
     */
    @GetMapping("/{userEmail}")
    public BaseResponse getReviews(@PathVariable String userEmail){
        BaseResponse response = null;
        try{
            User user = userService.findUser(userEmail);
            List<Review> reviewList = reviewService.findByUserIdOrderByReviewDate(user);
            List<ReviewDto> reviewDtoList = null;
            for (Review review : reviewList){
                ReviewDto reviewDto = new ReviewDto(review);//리뷰 정보 주입.
                ReviewImage reviewImage = reviewService.findReviewImageThumbnailByReviewId(review);//썸네일 얻어와서
                reviewDto.addThumbnail(reviewImage);
                reviewDtoList.add(reviewDto);
            }
            response = new BaseResponse("success", reviewDtoList);
        }catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * 정보 하나에 대한 Review 정보
     * @param reviewId
     * @return 해당 리뷰에 대한 정보와 ReviewImage 정보들
     */
    @GetMapping("/detail/{reviewId}")
    public BaseResponse getReviewDetail(@PathVariable Long reviewId){
        BaseResponse response = null;
        try {
            Review review = reviewService.findByReviewId(reviewId);
            List<ReviewImage> reviewImageList = reviewService.findReviewImageByReviewId(review);

            ReviewDto reviewDtoResult = new ReviewDto(review); //Review 정보 주입

            ReviewImageDto reviewImageDto = new ReviewImageDto();
            for(ReviewImage reviewImage : reviewImageList){
                reviewImageDto.addReviewImage(reviewImage);
            }
            reviewDtoResult.addReviewImage(reviewImageDto);//ReviewIamge 정보 주입

            response = new BaseResponse("success", reviewDtoResult);
        }catch (IllegalStateException e)
        {
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    /**
     * 리뷰 댓글 가져오기
     * @param reviewId
     * @return reviewCommentList
     */
    @GetMapping("/comment/{reviewId}")
    public BaseResponse getReviewCommentList(@PathVariable Long reviewId){
        BaseResponse response = null;
        try{
            Review review = reviewService.findByReviewId(reviewId);
            List<ReviewComment> reviewCommentList = reviewService.findReviewCommentByReviewId(review);
            response = new BaseResponse("success", reviewCommentList);
        }catch(IllegalStateException e)
        {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * ReviewComment 저장
     * @param reviewId 해당 리뷰의 id
     * @param reviewComment 저장할 reviewComment정보
     * @return 저장 성공 여부
     */
    @PostMapping("/comment/{reviewId}")
    public BaseResponse saveReviewComment(@PathVariable Long reviewId, @RequestBody ReviewComment reviewComment){
        BaseResponse response = null;
        try {
            Review review = reviewService.findByReviewId(reviewId);
            ReviewComment newReviewComment = new ReviewComment();
            newReviewComment.setUserId(review.getUserId());
            newReviewComment.setReviewId(review);
            reviewService.saveReviewComment(newReviewComment);
            response = new BaseResponse("success", newReviewComment);
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    //댓글 작성자만 수정할 수 있음.
    @PutMapping("/comment/{userEmail}")
    public BaseResponse updateReviewComment
    (@PathVariable String userEmail, @RequestBody ReviewComment reviewComment){
        BaseResponse response = null;
        try{
            User commentUser = reviewComment.getUserId(); //코멘트 작성자
            User loginUser = userService.findUser(userEmail); //현재 수정하려는 사용자
            if(loginUser.getUserId().equals(commentUser.getUserId())){
                response = new BaseResponse("success", reviewService.updateReviewComment(reviewComment));
            }else{
                response = new BaseResponse("fail", "동일한 작성자가 아닙니다.");
            }
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
