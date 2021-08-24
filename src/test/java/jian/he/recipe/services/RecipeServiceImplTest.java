package jian.he.recipe.services;

import jian.he.recipe.commands.RecipeCommand;
import jian.he.recipe.converters.RecipeCommandToRecipe;
import jian.he.recipe.converters.RecipeToRecipeCommand;
import jian.he.recipe.domain.Recipe;
import jian.he.recipe.repositroies.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository repository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        //set up an mock recipe repository.
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(repository, recipeCommandToRecipe, recipeToRecipeCommand);
    }
    @Test
    public void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(repository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
//        assertNotNull("Null recipe returned",recipeReturned);
        verify(repository,times(1)).findById(anyLong());
        verify(repository, never()).findAll();

    }

    @Test
    void findCommandById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional =Optional.of(recipe);
        when(repository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("Null recipe returned",commandById);
        verify(repository,times(1)).findById(anyLong());
        verify(repository, never()).findAll();

    }
}