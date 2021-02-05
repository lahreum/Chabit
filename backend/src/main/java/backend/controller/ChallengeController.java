package backend.controller;

import backend.domain.*;
import backend.service.CategoryService;
import backend.service.ChallengeService;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/challenges")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final UserService userService;
    private final CategoryService categoryService;

    // TODO: 인증샷 예시, 챌린지 썸네일 기능 추가시 변경 필요
    @PostMapping
    public BaseResponse makeChallenge(@RequestBody ChallengeRequest request) {
        BaseResponse response = null;
        // 가져온 정보 토대로 챌린지 생성
        try {
            User user = userService.findUser(request.getUserEmail());
            Category category = categoryService.findCategoryByName(request.getCategoryName());

            Challenge newChallenge = Challenge.createChallenge(user, request, category);
            newChallenge.join(new UserChallenge(user, newChallenge));

            challengeService.makeChallenge(newChallenge);

            response = new BaseResponse("success", "챌린지 생성 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @PostMapping("/category")
    public BaseResponse makeCategory(@RequestBody String categoryName){
        BaseResponse response = null;
        try {
            categoryService.makeCategory(new Category(categoryName));
            response = new BaseResponse("success", "카테고리 추가 성공");
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // ======= Response & Request 클래스 =======

}
