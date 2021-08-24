package jian.he.recipe.services;

import jian.he.recipe.commands.RecipeCommand;
import jian.he.recipe.converters.RecipeCommandToRecipe;
import jian.he.recipe.converters.RecipeToRecipeCommand;
import jian.he.recipe.domain.Recipe;
import jian.he.recipe.repositroies.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RecipeServiceTest {
    private  static final String NEW_DESCRIPTION = "New Description";
//    @Autowired
//    RecipeService recipeService;
//
//    @Autowired
//    RecipeRepository recipeRepository;
//    @Autowired
//    RecipeCommandToRecipe recipeCommandToRecipe;
//    @Autowired
//    RecipeToRecipeCommand recipeToRecipeCommand;
@Autowired
RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getRecipes() {
    }

    @Test
    void findById() {
    }

    @Test
    @Transactional
    void saveRecipeCommand() throws Exception{

        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());

        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());

    }
}