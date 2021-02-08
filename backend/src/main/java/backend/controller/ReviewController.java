package backend.controller;

import backend.domain.Challenge;
import backend.domain.User;
import backend.service.ChallengeService;
import backend.service.ReviewService;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/review")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ReviewController {
    private final UserService userService;
    private final ChallengeService challengeService;
    private final ReviewService reviewService;

    @PostMapping
    public BaseResponse makeReview(@RequestBody ReviewRequest request){
        BaseResponse response = null;
        try {
            User user = userService.findUser(request.getUserEamil());
//            Challenge challenge = challengeService.findbyChallengeId()

        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
