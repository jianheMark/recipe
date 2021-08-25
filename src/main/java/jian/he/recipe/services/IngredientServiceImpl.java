package jian.he.recipe.services;

import jian.he.recipe.commands.IngredientCommand;
import jian.he.recipe.converters.IngredientCommandToIngredient;
import jian.he.recipe.converters.IngredientToIngredientCommand;
import jian.he.recipe.domain.Ingredient;
import jian.he.recipe.domain.Recipe;
import jian.he.recipe.repositroies.RecipeRepository;
import jian.he.recipe.repositroies.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        //method have two parameters. One is recipeId, another one is ingredientId.
      Optional<Recipe> recipeOptional  = recipeRepository.findById(recipeId);

      if(!recipeOptional.isPresent()){
          log.error("recipe ID is not found. Id: " + recipeId);}
      Recipe recipe = recipeOptional.get();//get the recipe from the optional.
      Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientId))
              .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

      if(!ingredientCommandOptional.isPresent()){
          log.error("Ingredient id not found: " + ingredientId);
      }
      return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            // todo toss errors if not found.
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else{

            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(
                        unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId())
                                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))
                );
            }
            else{
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }
    }
}
