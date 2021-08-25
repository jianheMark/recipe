package jian.he.recipe.services;

import jian.he.recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
