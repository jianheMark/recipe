package jian.he.recipe.controllers;

import com.sun.jdi.PrimitiveValue;
import jian.he.recipe.domain.Category;
import jian.he.recipe.domain.UnitOfMeasure;
import jian.he.recipe.repositroies.CategoryRepository;
import jian.he.recipe.repositroies.UnitOfMeasureRepository;
import jian.he.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping({"","/index",""})
    public String getIndexPage(Model model){
        log.debug("Getting Index Page....");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
