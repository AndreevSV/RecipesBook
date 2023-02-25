package pro.sky.recipesbook.services;

import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;

import java.util.Objects;

public interface IngredientService {
    Object getIngredient(Integer id);

    SuccessMessageDto addIngredient(IngredientDto ingredientDto);
}
