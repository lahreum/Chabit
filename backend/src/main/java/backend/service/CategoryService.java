package backend.service;

import backend.domain.Category;
import backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
     public Long makeCategory(Category category)throws IllegalStateException{
         categoryRepository.save(category);
         return category.getCategoryId();
     }
    /**
     * 카테고리 목록 전부.
     * @param category
     * @return categoryList정보
     */
     public List<Category> findCategorys(){
         return categoryRepository.findAll();
     }
}
