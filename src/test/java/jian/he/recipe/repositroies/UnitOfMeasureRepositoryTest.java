package jian.he.recipe.repositroies;

import jian.he.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasure= unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon",unitOfMeasure.get().getDescription());
    }
    @Test
    void findByDescriptionEach() {
        Optional<UnitOfMeasure> unitOfMeasure= unitOfMeasureRepository.findByDescription("Each");

        assertEquals("Each",unitOfMeasure.get().getDescription());
    }
}