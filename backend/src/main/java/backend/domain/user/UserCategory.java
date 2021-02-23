package backend.domain.user;

import backend.domain.challenge.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(UserCategoryId.class)
@Getter @Setter
@NoArgsConstructor
public class UserCategory {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int successCount;

    public UserCategory(User user, Category category, int successCount) {
        this.user = user;
        this.category = category;
        this.successCount = successCount;
    }
}
