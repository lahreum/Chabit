package backend.domain.user;

import backend.controller.UserRequest;
import backend.domain.challenge.PointHistory;
import backend.exception.NotEnoughPointException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    private String userName;
    private String userNickname;
    private String userPhone;
    @Column(columnDefinition = "TEXT")
    private String userImage;
    private String userProfileMessage;

    @Column(columnDefinition = "INT DEFAULT 1000")
    private int userPoints;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDateTime userJoindate;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHashtag> hashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointHistory> pointHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proof> proofs = new ArrayList<>();

    // 한 유저가 카테고리별로 챌린지 몇번 성공했는지 저장
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategory> successCount = new ArrayList<>();

    // 유저가 갖고있는 뱃지 저장
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBadge> badges = new ArrayList<>();

    // 유저가 참여한 챌린지
    @OneToMany(mappedBy = "user")
    private List<UserChallenge> challenges = new ArrayList<>();

    // 생성 메서드
    public static User createUser(UserRequest request) {
        User user = new User();
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(request.getUserPassword());
        user.setUserName(request.getUserName());
        user.setUserNickname(request.getUserNickname());
        user.setUserPhone(request.getUserPhone());
        user.setUserRole(UserRole.USER);
        user.setUserPoints(1000);
        user.setUserJoindate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        user.setUserImage("https://ssafychabit.s3.ap-northeast-2.amazonaws.com/users/avatardefault_92824.png"); // 기본 프사

        return user;
    }

    // 연관관계 메서드
    public void addHashtag(UserHashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setUser(this);
    }

    public void addHistory(PointHistory history){
        history.setUser(this);
        this.pointHistories.add(history);
    }

    public void proofChallenge(Proof proof){
        this.proofs.add(proof);
        proof.setUser(this);
    }

    public void addSuccessCount(UserCategory userCategory) {
        this.successCount.add(userCategory);
        userCategory.setUser(this);
    }

    public void addBadge(UserBadge userBadge) {
        this.badges.add(userBadge);
        userBadge.setUser(this);
    }

    // 비즈니스 로직
    public void changePoint(int point){
        int restPoint = this.userPoints + point;
        if(restPoint < 0)
            throw new NotEnoughPointException("포인트가 부족합니다");
        this.userPoints = restPoint;
    }

    public void putProfileMessage(String userProfileMessage) {
        this.userProfileMessage = userProfileMessage;
    }
}