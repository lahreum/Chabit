package backend.domain.challenge;

import backend.controller.ChallengeRequest;
import backend.domain.user.User;
import backend.domain.user.UserChallenge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Challenge { // Challenge 코드리뷰 필수.

    @Id
    @GeneratedValue
    private Long challengeId;

    @Column(nullable = false)
    private String challengeName;
    @Column(columnDefinition = "TEXT")
    private String challengeDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="challenge_owner", nullable = false)
    private User challengeOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category challengeCategory;

    @Column(nullable = false)
    private LocalDateTime challengeStartdate;
    @Column(nullable = false)
    private LocalDateTime challengeEnddate;

    @Column(columnDefinition = "TEXT")
    private String challengeThumbnail;
    @Column(columnDefinition = "INT DEFAULT 100")
    private int challengePoint = 100;
    @Column(columnDefinition = "INT DEFAULT 0")
    private int challengeUsercount;

    @Column
    private String authWay;
    @Column(nullable = false)
    private int authFrequency;
    @Column
    private LocalTime authStarttime;
    @Column
    private LocalTime authEndtime;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private int authHoliday;
    @Column(columnDefinition = "TEXT")
    private String authExample;

    @Enumerated(EnumType.STRING)
    private ChallengeOngoing challengeOngoing;


    // === 연관관계 ===
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<UserChallenge> challengers = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeHashtag> hashtags = new ArrayList<>();


    // == 비즈니스 로직 ===
    // 챌린지 참여시 참여인원 1 증가
    public void join(UserChallenge userChallenge){
        this.challengers.add(userChallenge);
        userChallenge.setChallenge(this);
        this.challengeUsercount += 1;
    }

    // 해쉬태그 등록
    public void addHashtag(ChallengeHashtag challengeHashtag){
        this.hashtags.add(challengeHashtag);
        challengeHashtag.setChallenge(this);
    }


    // 생성 메서드
    public static Challenge createChallenge(User owner, ChallengeRequest request, Category category) {
        Challenge challenge = new Challenge();
        challenge.setChallengeName(request.getChallengeName());
        challenge.setChallengeDesc(request.getChallengeDesc());
        challenge.setChallengeOwner(owner);
        challenge.setChallengeCategory(category);
        challenge.setChallengeThumbnail(request.getChallengeThumbnail());
        challenge.setAuthExample(request.getAuthExample());

        LocalDate startdate= LocalDate.parse(request.getChallengeStartdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        challenge.setChallengeStartdate(LocalDateTime.of(startdate, LocalDateTime.MIN.toLocalTime()));

        LocalDate enddate= LocalDate.parse(request.getChallengeEnddate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        challenge.setChallengeEnddate(LocalDateTime.of(enddate, LocalDateTime.MIN.toLocalTime()));

        challenge.setAuthWay(request.getAuthWay());
        challenge.setAuthFrequency(request.getAuthFrequency());
        challenge.setAuthStarttime(LocalTime.parse(request.getAuthStarttime(),
                DateTimeFormatter.ofPattern("HH:mm")));
        challenge.setAuthEndtime(LocalTime.parse(request.getAuthEndtime(),
                DateTimeFormatter.ofPattern("HH:mm")));
        challenge.setAuthHoliday(request.isAuthHoliday() ? 1 : 0);

        if(challenge.challengeStartdate.toLocalDate().isEqual(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate())){
            challenge.setChallengeOngoing(ChallengeOngoing.ONGOING);
        } else {
            challenge.setChallengeOngoing(ChallengeOngoing.READY);
        }

        return challenge;
    }
}