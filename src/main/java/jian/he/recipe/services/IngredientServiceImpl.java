package jian.he.recipe.services;

import jian.he.recipe.commands.IngredientCommand;
import jian.he.recipe.converters.IngredientToIngredientCommand;
import jian.he.recipe.domain.Recipe;
import jian.he.recipe.repositroies.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
      Optional<Recipe> recipeOptional  = recipeRepository.findById(recipeId);
      if(!recipeOptional.isPresent()){
          log.error("recipe ID is not found. Id: " + recipeId);}
      Recipe recipe = recipeOptional.get();
      Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientId))
              .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

      if(!ingredientCommandOptional.isPresent()){
          log.error("Ingredient id not found: " + ingredientId);
      }
      return ingredientCommandOptional.get();
    }
}
