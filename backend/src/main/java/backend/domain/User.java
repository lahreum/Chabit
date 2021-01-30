package backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String userNickname;
    private String userPhone;

    @Column
    private int userPoints;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    private LocalDateTime userJoindate;

}