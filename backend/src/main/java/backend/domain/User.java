package backend.domain;

import backend.exception.NotEnoughPointException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(columnDefinition = "INT DEFAULT 1000")
    private int userPoints;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDateTime userJoindate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHashtag> hashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointHistory> pointHistories = new ArrayList<>();

    // 연관관계 메서드
    public void addHashtag(UserHashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setUser(this);
    }

    public void addHistory(PointHistory history){
        history.setUser(this);
        this.pointHistories.add(history);
    }

    // 비즈니스 로직
    public void changePoint(int point){
        int restPoint = this.userPoints + point;
        if(restPoint < 0)
            throw new NotEnoughPointException("포인트가 부족합니다");
        this.userPoints = restPoint;
    }

}