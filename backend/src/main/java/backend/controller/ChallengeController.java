package backend.controller;

import backend.domain.*;
import backend.exception.NotEnoughPointException;
import backend.service.CategoryService;
import backend.service.ChallengeService;
import backend.service.HashtagService;
import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/challenges")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;

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

            Challenge makeChallenge = challengeService.makeChallenge(newChallenge);// 챌린지 생성

            for (Hashtag hashtag : request.getHashtags()) {
                Hashtag findHashtag = hashtagService.findByHashtagId(hashtag.getHashtagId());
                challengeService.addHashtag(makeChallenge, findHashtag); // 챌린지에 해쉬태그 등록
            }

            response = new BaseResponse("success", "챌린지 생성 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 챌린지 참여
    @GetMapping("/{challengeId}/{userEmail}")
    public BaseResponse joinChallenge(@PathVariable Long challengeId, @PathVariable String userEmail){
        BaseResponse response = null;
        try {
            // 유저 정보
            User user = userService.findUser(userEmail);
            // 챌린지 정보
            Challenge challenge = challengeService.findByChallengeId(challengeId);
            // 챌린지 참여
            challengeService.joinChallenge(user, challenge);
            
            response = new BaseResponse("success", "챌린지 참가 완료");
        } catch (IllegalStateException | NotEnoughPointException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 카테고리 추가
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

    // 카테고리 목록 가져오기
    @GetMapping("/category")
    public BaseResponse getCategories(){
        BaseResponse response = null;
        try {
            List<Category> categories = categoryService.findCategories();
            response = new BaseResponse("success", categories);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 참여가능한 모든 챌린지 가져오기
    @GetMapping
    public BaseResponse getChallenges(){
        BaseResponse response = null;
        try {
            List<ChallengeDto> collect = challengeService.findChallenges().stream()
                    .filter(m -> m.getChallengeOngoing().equals(ChallengeOngoing.READY))
                    .sorted(Comparator.comparing(Challenge::getChallengeStartdate))
                    .map(ChallengeDto::new)
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 특정 챌린지 1개 가져오기
    @GetMapping("/{challengeId}")
    public BaseResponse getChallenge(@PathVariable Long challengeId) {
        BaseResponse response = null;
        try {
            Challenge findChallenge = challengeService.findByChallengeId(challengeId);
            if(findChallenge == null)
                response = new BaseResponse("fail", "잘못된 챌린지 아이디 입니다");
            else
                response = new BaseResponse("success", new ChallengeDto(findChallenge));
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 핫챌린지 4개 가져오기
    @GetMapping("/hot")
    public BaseResponse getHotChallenges(){
        BaseResponse response = null;
        try {
            List<ChallengeDto> collect = challengeService.findAllOrderByChallengeUserCount().stream()
                    .map(ChallengeDto::new)
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // ======= Response & Request 클래스 =======

}
