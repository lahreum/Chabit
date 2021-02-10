package backend.domain.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id //id값 String 형이라 직접대입.
    private String level;

    @Column(nullable = false)
    private int levelMinPoint;

    @Column(nullable = false)
    private int levelMaxPoint;

    @Column(columnDefinition = "TEXT")
    private String levelImage;
}
