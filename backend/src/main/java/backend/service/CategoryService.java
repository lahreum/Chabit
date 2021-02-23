package backend.service;

import backend.domain.challenge.Category;
import backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
     private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성.
     * @param category
     * @return categoryId
     */
     @Transactional
     public Long makeCategory(Category category) {
         Optional<Category> findCategory = categoryRepository.findByCategoryName(category.getCategoryName());
         if (findCategory.isPresent())
             throw new IllegalStateException("이미 존재하는 카테고리입니다");
         categoryRepository.save(category);
         return category.getCategoryId();
     }
    /**
     * 카테고리 목록 전부.
     * @return categoryList정보
     */
     public List<Category> findCategories(){
         return categoryRepository.findAll();
     }

    /**
     * 카테고리 이름으로 카테고리 찾기
     */
     public Category findCategoryByName(String categoryName) {
         Optional<Category> findCategory = categoryRepository.findByCategoryName(categoryName);
         if (findCategory.isPresent()) {
             return findCategory.get();
         } else {
             throw new IllegalStateException("잘못된 카테고리 명입니다.");
         }
     }

}
