package backend.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    @Column
    private String userEmail;
    @Column
    private String userPassword;
    @Column
    private int userPoints;
    @Column
    private String userRole;
    @Column
    private LocalDateTime userJoindate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getUserJoindate() {
        return userJoindate;
    }

    public void setUserJoindate(LocalDateTime userJoindate) {
        this.userJoindate = userJoindate;
    }
}