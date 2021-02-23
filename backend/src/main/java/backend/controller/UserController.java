package backend.controller;
//http://localhost:9999/swagger-ui.html
import backend.domain.badge.Badge;
import backend.domain.badge.BadgeResponse;
import backend.domain.challenge.Challenge;
import backend.domain.challenge.ChallengeDto;
import backend.domain.challenge.ChallengeHashtag;
import backend.domain.challenge.ChallengeResult;
import backend.domain.hashtag.HashtagDto;
import backend.domain.user.*;
import backend.service.BadgeService;
import backend.service.LevelService;
import backend.service.UserService;
import backend.utils.Uploader;
import backend.utils.Validator;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LevelService levelService;
    private final BadgeService badgeService;
    private final Uploader uploader;
    private final Validator validator;

    @ApiOperation(value="모든 사용자 조회", notes="모든 사용자 조회")
    @GetMapping
    public BaseResponse users() {
        List<User> findUsers = userService.findUsers();

        // 엔티티 -> DTO 변환
        List<UserDto> collect = findUsers.stream()
                .map(m -> new UserDto(m.getUserEmail(), m.getUserPassword(), m.getUserNickname(), m.getUserName(), m.getUserPhone(), m.getUserPoints(), m.getUserJoindate(), m.getUserRole()))
                .collect(Collectors.toList());

        // 레벨 추가
        for(UserDto dto : collect) {
            String userLevel = levelService.findUserLevel(dto.getUserPoints());
            Optional<Level> level = levelService.findOne(userLevel);
            level.ifPresent(value -> dto.addUserLevel(new LevelDto(value)));
        }

        return new BaseResponse("success", collect);
    }

    @ApiOperation(value="닉네임으로 유저 찾기", notes="닉네임 중복 체크 용")
    @ApiImplicitParam(name = "nickname", value = "사용자 닉네임", required = true)
    @GetMapping("/nickname/{nickname}")
    public BaseResponse chekcNickname(@PathVariable String nickname){
        BaseResponse response = null;
        try {
            User user = userService.findUserByNickname(nickname);
            response = new BaseResponse("success", new UserDto(user));
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value="유저 닉네임 검색", notes="유저 검색용. 일부만 포함되도 찾도록 설정")
    @ApiImplicitParam(name = "nickname", value = "사용자 닉네임", required = true)
    @GetMapping("/search/{nickname}")
    public BaseResponse searchByNickname(@PathVariable String nickname){
        BaseResponse response = null;
        try {
            List<User> findUsers = userService.findUserContainNickname(nickname);
            List<UserDto> result = new ArrayList<>();

            for (User user : findUsers) {
                UserDto userDto = new UserDto(user);

                String userLevel = levelService.findUserLevel(userDto.getUserPoints());
                Optional<Level> level = levelService.findOne(userLevel);
                level.ifPresent(l -> userDto.addUserLevel(new LevelDto(l)));

                result.add(userDto);
            }

            response = new BaseResponse("success", result);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value="사용자 한명 조회", notes="사용자 한명 조회")
    @ApiImplicitParam(name = "userEmail", value = "사용자 이메일", required = true)
    @GetMapping("/{userEmail}")
    public BaseResponse user(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            // 유저 찾기
            User findUser = userService.findUser(userEmail);

            // 해당 유저의 해쉬태그 가져오기
            List<UserHashtag> findHashtagList = userService.findHashtag(userEmail);

            // 엔티티 -> DTO
            HashtagDto hashtagDto = new HashtagDto();
            findHashtagList.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));

            UserDto userDto = new UserDto(findUser);
            userDto.setUserPassword(findUser.getUserPassword());
            userDto.addHashtags(hashtagDto);

            // 해당 유저의 레벨 가져오기.
            String userLevel = levelService.findUserLevel(userDto.getUserPoints());
            Optional<Level> level = levelService.findOne(userLevel);
            level.ifPresent(l -> userDto.addUserLevel(new LevelDto(l)));
            
            // 뱃지 추가
            BadgeResponse badgeDto = new BadgeResponse();
            List<Badge> allBadge = badgeService.findAll(); // 모든 뱃지
            List<UserBadge> userBadges = findUser.getBadges(); // 유저가 딴 뱃지

            for (Badge badge : allBadge) {
                boolean userGet = false;
                for (UserBadge userBadge : userBadges) {
                    if (badge.getBadgeId().equals(userBadge.getBadge().getBadgeId())) {
                        userGet = true;
                        badgeDto.addBadge(badge, userGet);
                        break;
                    }
                }
                if (!userGet)
                    badgeDto.addBadge(badge, userGet);
            }

            userDto.addBadges(badgeDto);

            response = new BaseResponse("success", userDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @PostMapping
    @ApiOperation(value="회원가입", notes="회원가입")
    @ApiImplicitParam(name = "UserRequest", value = "사용자 정보", required = true)
    public BaseResponse signIn(@RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            request.setUserPhone(validator.phoneValidator(request.getUserPhone())); // 전화번호 변환
            User user = User.createUser(request);

            userService.signIn(user);
            userService.initSuccessCount(user); // 카테고리별 챌린지 성공 횟수 초기화

            response = new BaseResponse("success", new JoinUserResponse("success", "회원가입 성공"));
        } catch (IllegalStateException e) {
            response = new BaseResponse("success", new JoinUserResponse("fail", e.getMessage()));
        }

        return response;
    }

    @PutMapping("/{userEmail}")
    @ApiOperation(value="사용자 정보 수정", notes="사용자 정보 수정")
    public BaseResponse updateUser(@PathVariable String userEmail, @RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            request.setUserEmail(userEmail);
            request.setUserPhone(validator.phoneValidator(request.getUserPhone())); // 전화번호 변환
            userService.updateUser(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    @PatchMapping("/{userEmail}/profile")
    @ApiOperation(value = "사용자 상태메세지 추가 & 수정", notes = "사용자 상태메세지 추가 & 수정")
    public BaseResponse updateUserProfileMessage(@PathVariable String userEmail, @RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            userService.putProfileMessage(user, request.getUserProfileMessage());

            response = new BaseResponse("success", "성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{userEmail}")
    @ApiOperation(value="사용자 삭제", notes="회원탈퇴용")
    public BaseResponse deleteUser(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            userService.deleteUser(userEmail);
            response = new BaseResponse("success", new DeleteUserResponse("success", "삭제 성공"));
        } catch (IllegalStateException e) {
            response = new BaseResponse("success", new DeleteUserResponse("fail", e.getMessage()));
        }

        return response;
    }

    @PostMapping("/hashtag/{userEmail}")
    @ApiOperation(value="해쉬태그 등록", notes="해쉬태그 등록")
    public BaseResponse saveHashtag(@PathVariable String userEmail, @RequestParam(required = false, defaultValue = "") String hashtagName) {
        BaseResponse response = null;
        if(hashtagName.equals(""))
            return new BaseResponse("fail", "해쉬태그 이름을 입력해주세요");
        try {
            userService.addHashtag(userEmail, hashtagName);
            response = new BaseResponse("success", "추가 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @GetMapping("/hashtag/{userEmail}")
    @ApiOperation(value="유저 해쉬태그 조회", notes="유저 해쉬태그 가져오기")
    public BaseResponse getUserHashtag(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            List<UserHashtag> findHashtagList = userService.findHashtag(userEmail);

            // 엔티티 -> DTO
            HashtagDto hashtagDto = new HashtagDto();
            findHashtagList.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));
            response = new BaseResponse("success", hashtagDto);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/hashtag/{userEmail}/{hashtagName}")
    @ApiOperation(value="유저 해쉬태그 삭제", notes="유저 해쉬태그 삭제")
    public BaseResponse deleteHashtag(@PathVariable String userEmail, @PathVariable String hashtagName) {
        BaseResponse response = null;
        try {
            userService.removeHashtag(userEmail, hashtagName);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }

        return response;
    }

    // 랭킹
    @GetMapping("/ranking/{userEmail}")
    @ApiOperation(value="랭킹 조회", notes="랭킹 조회. 조건별 조회 가능")
    public BaseResponse getRanking(@PathVariable String userEmail,
                                   @RequestParam Long categoryId,
                                   @RequestParam(required = false, defaultValue = "false") Boolean monthlyRanking,
                                   @RequestParam(required = false, defaultValue = "false") Boolean onlyFollowing) {
        BaseResponse response = null;
        try {
            List<UserDto> ranking = userService.findUserByRankingCondition(userEmail, categoryId, monthlyRanking, onlyFollowing);
            
            // 내 랭킹 찾기
            int myRank = 1;
            boolean findMe = false;
            for (UserDto user : ranking) {
                if(user.getUserEmail().equals(userEmail)) {
                    findMe = true;
                    break;
                }
                myRank++;
            }

            if (!findMe) {
                User me = userService.findUser(userEmail);
                ranking.add(new UserDto(me, 0));
            }

            response = new BaseResponse("success", new RankingResponse(myRank, ranking));
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // 프로필 사진 등록
    @PostMapping("/{userEmail}/image")
    @ApiOperation(value="프로필 사진 등록 & 수정", notes="프로필 사진 등록 & 수정하기")
    public BaseResponse setUserImage(@PathVariable String userEmail, @RequestPart(value = "userImage", required = false) MultipartFile userImage){
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            String uniqueName = user.getUserId() + "_userImage_" + LocalDate.now(ZoneId.of("Asia/Seoul")) + "_";
            String imageUrl = uploader.upload(userImage, "users", uniqueName);

            userService.putUserImage(user, imageUrl);
            response = new BaseResponse("success", imageUrl);
        } catch (IllegalStateException | IOException | IllegalArgumentException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @GetMapping("/{userEmail}/challenges")
    @ApiOperation(value="유저가 참여한 챌린지 조회", notes="진행중인 챌린지(ongoing)/ 종료된 챌린지(terminated)/ 개설한 챌린지(own) 목록 제공")
    public BaseResponse getUserChallenges(@PathVariable String userEmail) {
        BaseResponse response = null;
        try {
            User user = userService.findUser(userEmail);
            List<UserChallenge> challenges = user.getChallenges();
            List<ChallengeDto> ongoingChallenges = new ArrayList<>(); // 진행중인 챌린지
            List<ChallengeDto> terminatedChallenges = new ArrayList<>(); // 종료된 챌린지
            List<ChallengeDto> ownChallenges = new ArrayList<>(); // 개설한 챌린지

            for (UserChallenge uc : challenges) {
                Challenge challenge = uc.getChallenge();
                ChallengeDto dto = new ChallengeDto(challenge);

                // 해쉬태그
                List<ChallengeHashtag> hashtags = challenge.getHashtags();
                HashtagDto hashtagDto = new HashtagDto();
                hashtags.forEach(h -> hashtagDto.addHashtag(h.getHashtag()));
                dto.addHashtag(hashtagDto);

                // 챌린지 상태
                ChallengeResult challengeResult = uc.getUserChallengeResult();
                if (challengeResult.equals(ChallengeResult.READY)) {
                    dto.addChallengeStatus("JOIN");
                    ongoingChallenges.add(dto);
                } else if (challengeResult.equals(ChallengeResult.SUCCESS)) {
                    dto.addChallengeStatus("SUCCESS");
                    terminatedChallenges.add(dto);
                } else {
                    dto.addChallengeStatus("FAIL");
                    terminatedChallenges.add(dto);
                }

                // 내가 개설한 챌린지
                if (challenge.getChallengeOwner().getUserEmail().equals(userEmail))
                    ownChallenges.add(dto);
            }

            JSONObject obj = new JSONObject();
            obj.put("ongoingChallenge", ongoingChallenges);
            obj.put("terminatedChallenge", terminatedChallenges);
            obj.put("ownChallenge", ownChallenges);

            response = new BaseResponse("success", obj);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    // ======= Response & Request 클래스 =======
    // 회원 가입
    @Data
    @ApiModel
    static class JoinUserResponse {
        @ApiModelProperty(value = "회원가입 결과")
        private String joinResult;
        @ApiModelProperty(value = "회원가입 결과 메세지")
        private String message; 

        public JoinUserResponse() {
        }

        public JoinUserResponse(String joinResult, String message) {
            this.joinResult = joinResult;
            this.message = message;
        }
    }

    // 회원 탈퇴
    @Data
    @AllArgsConstructor
    @ApiModel
    static class DeleteUserResponse {
        @ApiModelProperty(value = "탈퇴 결과")
        private String deleteResult;
        @ApiModelProperty(value = "탈퇴 결과 메세지")
        private String message;
    }

    // 랭킹
    @Data
    @AllArgsConstructor
    @ApiModel
    static class RankingResponse {
        @ApiModelProperty(value = "내 랭킹")
        private int myRanking;
        @ApiModelProperty(value = "랭킹")
        List<UserDto> userRanking;
    }
}
