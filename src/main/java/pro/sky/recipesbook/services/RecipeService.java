package pro.sky.recipesbook.services;

import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Recipe;

import java.util.List;

public interface RecipeService {

    Object getRecipe(Integer id);

    List<Recipe> getAllRecipes();

    SuccessMessageDto addRecipe(RecipeDto recipeDto);

    SuccessMessageDto editRecipe(Integer id, RecipeDto recipeDto);

    SuccessMessageDto deleteRecipe(Integer id);

    SuccessMessageDto deleteAllRecipes();
}
