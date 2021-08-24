package jian.he.recipe.services;

import jian.he.recipe.commands.RecipeCommand;
import jian.he.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);


    RecipeCommand findCommandById(Long l);

    void deleteById(Long l);
}
