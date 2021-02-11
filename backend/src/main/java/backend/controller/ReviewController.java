package backend.controller;

import backend.domain.challenge.Challenge;
import backend.domain.review.*;
import backend.domain.user.User;
import backend.service.ChallengeService;
import backend.service.ReviewService;
import backend.service.UserService;
import backend.utils.Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@RequestMapping("/v1/review")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ReviewController {
    private final UserService userService;
    private final ChallengeService challengeService;
    private final ReviewService reviewService;
    private final Uploader uploader;

    /**
     * 리뷰 생성
     *
     * @param request : 리뷰 컨텐트, 유저 이메일정보와 챌린지 Id정보
     * @return
     */
    @PostMapping
    @ApiOperation(value = "리뷰 작성", notes = "리뷰 작성")
    public BaseResponse makeReview(ReviewRequest request) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(request.getUserEmail()); //해당 리뷰 유저 정보
            Challenge challenge = challengeService.findByChallengeId(request.getChallengeId()); // 어떤 챌린지 참가했는가.

            Review newReview = Review.createReview(user, challenge, request.getReviewContent()); //리뷰를 만든다.
            Review saveReview = reviewService.saveReview(newReview);//저장

            List<String> urls = new ArrayList<>();

            for (MultipartFile file : request.getReviewImages()) {
                String unique = "reviewImage_" + user.getUserId() + "_" + challenge.getChallengeId() + "_" + LocalDateTime.now() + "_";
                String reviews = uploader.upload(file, "reviews", unique);

                urls.add(reviews);
            }
            reviewService.addReviewImages(saveReview, urls);

            response = new BaseResponse("success", "저장성공");
        } catch (IllegalStateException | IOException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }


    /**
     * 해당 유저 마이리뷰 목록 불러오기
     * 해당 유저 email을 받아오면
     *
     * @param userEmail
     * @return 해당유저 review의 List(최신 순으로 정렬)
     */
    @GetMapping("/{userEmail}")
    public BaseResponse getReviews(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            List<Review> reviewList = reviewService.findByUserIdOrderByReviewDate(user);
            if (reviewList.size() != 0) {
                List<ReviewDto> reviewDtoList = new ArrayList<>();
                for (Review review : reviewList) {
                    ReviewDto reviewDto = new ReviewDto(review);//리뷰 정보 주입.
                    review.getReviewImageList().forEach(reviewDto::addReviewImage);

                    reviewDtoList.add(reviewDto);
                }
                response = new BaseResponse("success", reviewDtoList);
            } else {
                response = new BaseResponse("success", "데이터가 없습니다.");
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * 정보 하나에 대한 Review 정보
     *
     * @param reviewId
     * @return 해당 리뷰에 대한 정보와 ReviewImage 정보들
     */
    @GetMapping("/detail/{reviewId}")
    public BaseResponse getReviewDetail(@PathVariable Long reviewId, @RequestParam(required = false, defaultValue = "") String userEmail) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            Review review = reviewService.findByReviewId(reviewId);

            ReviewDto reviewDtoResult = new ReviewDto(review); //Review 정보 주입
            // 좋아요 눌렀나 체크
            List<Cool> coolList = review.getCoolList();
            for (Cool c : coolList) {
                if (c.getUserId().getUserEmail().equals(userEmail)) {
                    reviewDtoResult.setPressCool(true);
                }
            }

            review.getReviewImageList().forEach(reviewDtoResult::addReviewImage);

            response = new BaseResponse("success", reviewDtoResult);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * 리뷰 댓글 가져오기
     *
     * @param reviewId
     * @return reviewCommentList
     */
    @GetMapping("/comment/{reviewId}")
    public BaseResponse getReviewCommentList(@PathVariable Long reviewId) {
        BaseResponse response = null;
        try {
            Review review = reviewService.findByReviewId(reviewId);
            List<ReviewComment> reviewCommentList = reviewService.findReviewCommentByReviewId(review);
            List<ReviewCommentDto> reviewCommentDtoList = new ArrayList<>();
            for (ReviewComment reviewComment : reviewCommentList) {
                ReviewCommentDto newReviewCommentDto = new ReviewCommentDto(reviewComment);
                reviewCommentDtoList.add(newReviewCommentDto);
            }
            response = new BaseResponse("success", reviewCommentDtoList);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * ReviewComment 저장
     *
     * @param reviewId       해당 리뷰의 id
     * @param commentContent 저장할 reviewComment정보
     * @return 저장 성공 여부
     */
    @PostMapping("/comment/{reviewId}")
    public BaseResponse saveReviewComment(@PathVariable Long reviewId, @RequestBody String commentContent) {
        BaseResponse response = null;
        try {
            Review review = reviewService.findByReviewId(reviewId); //아이디값으로 어떤 review인가 구한다.
            ReviewComment newReviewComment = new ReviewComment();//새 리뷰답장을 만든다.
            newReviewComment.setUserId(review.getUserId());
            newReviewComment.setReviewId(review);
            newReviewComment.setCommentContent(commentContent);
            reviewService.saveReviewComment(newReviewComment);
            response = new BaseResponse("success", "저장성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    //댓글 작성자만 수정할 수 있음.

    /**
     * @param reviewCommentId 수정할 리뷰코멘트의 id값
     * @param request         userEmail 현재 로그인한 사용자의 eamil정보.
     *                        + reviewCommentContent 수정될 Comment에 대한 내용를 가져온다.
     * @return 반환 되는 값은 업데이트 된 정보 또는 에러메시지
     */
    @PutMapping("/comment/{reviewCommentId}")
    public BaseResponse updateReviewComment
    (@PathVariable Long reviewCommentId, @RequestBody ReviewRequest request) {
        BaseResponse response = null;
        try {
            User commentUser = reviewService.findReviewCommentByReviewCommentId(reviewCommentId).getUserId();
            //reviewCommentId로 리뷰코멘트 데이터 -> 코멘트 작성자Id찾기
            User loginUser = userService.findUser(request.getUserEmail()); //현재 수정하려는 사용자
            if (loginUser.getUserId().equals(commentUser.getUserId())) {
                ReviewComment reviewComment = new ReviewComment();
                reviewComment.setReviewCommentId(reviewCommentId);
                reviewComment.setCommentContent(request.getReviewContent());
                reviewService.updateReviewComment(reviewComment);
                response = new BaseResponse("success", "수정성공");
            } else {
                response = new BaseResponse("fail", "동일한 작성자가 아닙니다.");
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * @param loginUserEmail  현재 로그인한 사용자의 eamil정보.
     * @param reviewCommentId 삭제될 Comment에 대한 Id 정보
     * @return 삭제 되었는가에 대한 정보
     */
    @DeleteMapping("/comment/{userEmail}/{reviewCommentId}")
    public BaseResponse deleteReviewComment
    (@PathVariable String loginUserEmail, @PathVariable Long reviewCommentId) {
        BaseResponse response = null;
        try {
            User reviewUser = reviewService.findReviewCommentByReviewCommentId(reviewCommentId).getReviewId().getUserId(); //리뷰 작성자
            User commentUser = reviewService.findReviewCommentByReviewCommentId(reviewCommentId).getUserId();
            //reviewCommentId로 리뷰코멘트 데이터 -> 코멘트 작성자Id찾기
            User loginUser = userService.findUser(loginUserEmail); //현재 수정하려는 사용자
            if (loginUser.getUserId().equals(reviewUser.getUserId()) || loginUser.getUserId().equals(commentUser.getUserId())) {
                ReviewComment reviewComment = reviewService.findReviewCommentByReviewCommentId(reviewCommentId);
                reviewService.deleteReviewComment(reviewComment);
                response = new BaseResponse("success", "삭제되었습니다.");
            } else {
                response = new BaseResponse("fail", "동일한 작성자 또는 리뷰 작성자가 아닙니다.");
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * 리뷰 멋져오 누르기 (누른 사람이 또 누르면 취소)
     */
    @PostMapping("/{reviewId}/cool")
    public BaseResponse pressCool(@PathVariable Long reviewId, @RequestParam(required = false, defaultValue = "") String userEmail) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            Review review = reviewService.findByReviewId(reviewId);

            reviewService.pressCool(review, user);

            response = new BaseResponse("success", "성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

}
