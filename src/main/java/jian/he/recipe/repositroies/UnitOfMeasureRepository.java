package jian.he.recipe.repositroies;

import jian.he.recipe.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
