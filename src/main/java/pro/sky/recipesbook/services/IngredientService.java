package pro.sky.recipesbook.services;

import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Object getIngredient(Integer id);

    List<Ingredient> getAllIngredients();

    SuccessMessageDto addIngredient(IngredientDto ingredientDto);

    SuccessMessageDto editIngredient(Integer id, IngredientDto ingredientDto);

    SuccessMessageDto deleteIngredient(Integer id);

    SuccessMessageDto deleteAllIngredients();
}
