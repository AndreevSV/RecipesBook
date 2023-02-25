package pro.sky.recipesbook.services;

import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;

public interface RecipeService {

    Object getRecipe(Integer id);

    SuccessMessageDto addRecipe(RecipeDto recipeDto);
}
