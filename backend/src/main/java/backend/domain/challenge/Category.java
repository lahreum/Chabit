package backend.domain.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long categoryId;

    @Column(nullable = false)
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
