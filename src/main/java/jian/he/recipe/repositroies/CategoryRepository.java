package jian.he.recipe.repositroies;

import jian.he.recipe.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByDescription(String description);
}
