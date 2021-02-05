package backend.domain;

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

    @Column
    private int userPoints;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    private LocalDateTime userJoindate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHashtag> hashtags = new ArrayList<>();


    // 연관관계 메서드
    public void addHashtag(UserHashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setUser(this);
    }

}