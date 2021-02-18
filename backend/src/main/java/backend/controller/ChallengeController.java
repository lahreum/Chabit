package backend.controller;

import backend.domain.challenge.*;
import backend.domain.hashtag.Hashtag;
import backend.domain.hashtag.HashtagDto;
import backend.domain.review.Review;
import backend.domain.user.*;
import backend.exception.NotEnoughPointException;
import backend.service.*;
import backend.utils.Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/v1/challenges")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final ProofService proofService;
    private final ReviewService reviewService;
    private final Uploader uploader;


    @PostMapping
    @ApiOperation(value="챌린지 생성", notes="챌린지 생성")
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

    @PostMapping("/thumbnail")
    @ApiOperation(value = "챌린지 썸네일 등록", notes = "챌린지 썸네일 등록")
    public BaseResponse putChallengeThumbnail(@RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {
        BaseResponse response = null;
        try {
            String unique = "thumbnail_" + LocalDateTime.now(ZoneId.of("Asia/Seoul")) + "_" + (int)(Math.random() * 100000) + "_";
            String thumbnailUrl = uploader.upload(thumbnail, "challenges", unique);

            response = new BaseResponse("success", thumbnailUrl);
        } catch (IllegalStateException | IOException | IllegalArgumentException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @PostMapping("/authExample")
    @ApiOperation(value = "챌린지 인증 예시 등록", notes = "챌린지 인증 예시 사진 등록")
    public BaseResponse putChallengeAuthExample(@RequestPart(value = "authExample", required = false) MultipartFile authExample) {
        BaseResponse response = null;
        try {
            String unique = "authExample_" + LocalDateTime.now(ZoneId.of("Asia/Seoul")) + "_" + (int)(Math.random() * 100000) + "_";
            String authExampleUrl = uploader.upload(authExample, "challenges", unique);

            response = new BaseResponse("success", authExampleUrl);
        } catch (IllegalStateException | IOException | IllegalArgumentException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @GetMapping("/todayProof")
    @ApiOperation(value = "오늘의 손모양 ", notes = "오늘의 손모양 사진 조회")
    public BaseResponse getTodayProof() {
        BaseResponse response = null;
        try {
            List<ProofExample> todayProofList = proofService.getTodayProofList();

            Random rand = new Random();
            rand.setSeed(LocalDate.now(ZoneId.of("Asia/Seoul")).toEpochDay());

            ProofExample todayProof = todayProofList.get(rand.nextInt(todayProofList.size()));

            response = new BaseResponse("success", todayProof);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    // 챌린지 참여
    @GetMapping("/{challengeId}/{userEmail}")
    @ApiOperation(value="챌린지 참여", notes="챌린지 참여")
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

    // 챌린지 인증
    @PostMapping("/{challengeId}/proof/{userEmail}")
    @ApiOperation(value="챌린지 인증", notes="챌린지 인증 등록")
    public BaseResponse proofChallenge(@PathVariable Long challengeId, @PathVariable String userEmail, @RequestPart("proofImage") MultipartFile file){
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            Challenge challenge = challengeService.findByChallengeId(challengeId);

            if(challenge == null)
                response = new BaseResponse("fail", "잘못된 챌린지 아이디입니다");
            else {
                String uniqueName = user.getUserId() + "_" + challengeId + "_" + LocalDate.now(ZoneId.of("Asia/Seoul")) + "_";
                String proofUrl = uploader.upload(file, "proofs", uniqueName);
                userService.proofChallenge(user, challenge, proofUrl);
                response = new BaseResponse("success", "인증 성공");
            }
        } catch (IllegalStateException | IOException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 챌린지 인증 목록 가져오기
    @GetMapping("/{challengeId}/proof")
    @ApiOperation(value="챌린지 인증 목록", notes="한 챌린지에 등록된 모든 인증 목록 조회")
    public BaseResponse getProofs(@PathVariable Long challengeId){
        BaseResponse response = null;
        try {
            List<Proof> proofList = proofService.findAllByChallengeId(challengeId);
            List<ProofDto> collect = proofList.stream()
                    .map(ProofDto::new)
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 챌린지 인증 취소
    @DeleteMapping("/{challengeId}/proof/{proofId}")
    @ApiOperation(value="챌린지 인증 반려", notes="챌린지에 등록한 인증 반려")
    public BaseResponse proofCancel(@PathVariable Long challengeId, @PathVariable Long proofId, @RequestParam(required = false) String ownerEmail){
        BaseResponse response = null;
        if(ownerEmail == null)
            return new BaseResponse("fail", "개설자 이메일이 없습니다");
        try {
            User owner = userService.findUser(ownerEmail);
            Challenge challenge = challengeService.findByChallengeId(challengeId);

            if (!challenge.getChallengeOwner().getUserEmail().equals(ownerEmail))
                response = new BaseResponse("fail", "챌린지 개설자가 아닙니다");
            else {
                // 해당 인증 삭제
                proofService.deleteProof(proofId);
                response = new BaseResponse("success", "인증 취소 완료");
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 카테고리 추가
    @PostMapping("/category")
    @ApiOperation(value="카테고리 생성", notes="챌린지 카테고리 생성")
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
    @ApiOperation(value="카테고리 목록 조회", notes="카테고리 목록 조회")
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

    // 진행 예정, 진행중인 모든 챌린지 가져오기
    @GetMapping
    @ApiOperation(value="챌린지 목록 조회", notes="진행중 & 진행 예정 중인 모든 챌린지 조회")
    public BaseResponse getChallenges(@RequestParam(required = false) String challengeName){
        BaseResponse response = null;
        try {
            List<Challenge> challenges = challengeService.findChallenges().stream()
                    .filter(m -> (challengeName == null || m.getChallengeName().contains(challengeName)) &&
                            (m.getChallengeOngoing().equals(ChallengeOngoing.READY) || m.getChallengeOngoing().equals(ChallengeOngoing.ONGOING)))
                    .sorted(Comparator.comparing(Challenge::getChallengeStartdate))
                    .collect(Collectors.toList());

            List<ChallengeDto> collect = new ArrayList<>();
            for (Challenge challenge : challenges) {
                List<ChallengeHashtag> hashtags = challenge.getHashtags();
                HashtagDto hashtagDto = new HashtagDto();

                hashtags.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));

                ChallengeDto dto = new ChallengeDto(challenge);
                dto.addHashtag(hashtagDto);

                collect.add(dto);
            }
            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }



    // 특정 챌린지 1개 가져오기
    @GetMapping("/{challengeId}")
    @ApiOperation(value="챌린지 상세 정보 조회", notes="특정 챌린지 1개 조회")
    public BaseResponse getChallenge(@PathVariable Long challengeId, @RequestParam(required = false, defaultValue = "") String userEmail) {
        BaseResponse response = null;
        try {
            Challenge findChallenge = challengeService.findByChallengeId(challengeId);
            if(findChallenge == null)
                response = new BaseResponse("fail", "잘못된 챌린지 아이디 입니다");
            else {
                // 해시태그 추가
                List<ChallengeHashtag> hashtags = findChallenge.getHashtags();
                HashtagDto hashtagDto = new HashtagDto();
                hashtags.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));

                ChallengeDto dto = new ChallengeDto(findChallenge);
                dto.addHashtag(hashtagDto);

                // 유저 참여 정보 찾기
                if (!userEmail.equals("")) {
                    User user = userService.findUser(userEmail);
                    List<UserChallenge> challengers = findChallenge.getChallengers();

                    String challengeJoinStatus = null;
                    String reviewStatus = null;
                    for (UserChallenge uc : challengers) {
                        if (uc.getUser().getUserEmail().equals(userEmail)) {
                            // 챌린지 참여자 중에 해당유저 있음
                            ChallengeResult userStatus = uc.getUserChallengeResult();
                            if (userStatus.equals(ChallengeResult.READY)) {
                                challengeJoinStatus = "JOIN";
                            } else if (userStatus.equals(ChallengeResult.FAIL)) {
                                challengeJoinStatus = "FAIL";
                            } else {
                                challengeJoinStatus = "SUCCESS";
                                // 리뷰 작성 여부 판단

                                Optional<Review> review = reviewService.findByUserIdAndChallengeId(user.getUserId(), findChallenge.getChallengeId());
                                if (review.isPresent())
                                    reviewStatus = "DONE";
                                else
                                    reviewStatus = "YET";
                            }
                            break;
                        }
                    }
                    if (challengeJoinStatus == null)
                        challengeJoinStatus = "NO";
                    if (reviewStatus == null)
                        reviewStatus = "CANT";

                    dto.addUserStatus(challengeJoinStatus, reviewStatus);
                }
                response = new BaseResponse("success", dto);
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 핫챌린지 4개 가져오기
    @GetMapping("/hot")
    @ApiOperation(value="핫 챌린지 조회", notes="인기 상위 챌린지 4개 조회")
    public BaseResponse getHotChallenges(){
        BaseResponse response = null;
        try {
            List<Challenge> challenges = challengeService.findAllOrderByChallengeUserCount();
            List<ChallengeDto> collect = new ArrayList<>();

            for (Challenge challenge : challenges) {
                if (!challenge.getChallengeOngoing().equals(ChallengeOngoing.END)) {
                    List<ChallengeHashtag> hashtags = challenge.getHashtags();
                    HashtagDto hashtagDto = new HashtagDto();
                    hashtags.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));

                    ChallengeDto dto = new ChallengeDto(challenge);
                    dto.addHashtag(hashtagDto);

                    collect.add(dto);
                }
            }

            response = new BaseResponse("success", collect);
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 오늘까지인 챌린지 종료
    @PostMapping("/done")
    @ApiOperation(value="챌린지 종료", notes="오늘까지인 모든 챌린지 종료 후 포인트 산정")
    public BaseResponse endChallenge(){
        BaseResponse response;
        try {
            List<User> winners = challengeService.endChallenges();
            challengeService.giveBadges(winners);
            response = new BaseResponse("success", "성공");
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
    // 오늘부터 시작인 챌린지 시작
    @PostMapping("/start")
    @ApiOperation(value="챌린지 시작", notes="오늘부터 시작인 챌린지들 시작")
    public BaseResponse startChallenge() {
        BaseResponse response = null;
        try {
            challengeService.startChallenges();
            response = new BaseResponse("success", "챌린지 시작");
        } catch (IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
