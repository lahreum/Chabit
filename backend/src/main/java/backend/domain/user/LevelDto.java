package backend.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LevelDto {
    private String level;
    private int levelMaxPoint;
    private String levelImage;

    public LevelDto (Level level) {
        this.level = level.getLevel();
        this.levelMaxPoint = level.getLevelMaxPoint();
        this.levelImage = level.getLevelImage();
    }
}
