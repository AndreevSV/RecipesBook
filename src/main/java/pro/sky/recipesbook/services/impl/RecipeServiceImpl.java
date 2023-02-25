package pro.sky.recipesbook.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.model.Recipe;
import pro.sky.recipesbook.repository.IngredientRepository;
import pro.sky.recipesbook.repository.RecipeRepository;
import pro.sky.recipesbook.services.RecipeService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;


    @Override
    public Object getRecipe(Integer id) {
        Recipe recipe = recipeRepository.getRecipe(id);
        if (recipe != null)
            return recipe;
        return new SuccessMessageDto(false, "Recipe with this Id not found");
    }

    @Override
    public SuccessMessageDto addRecipe(RecipeDto recipeDto) {
        if (recipeDto.name() == null || recipeDto.time() <=0) {
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        for (String str : recipeDto.steps()) {
            if (str == null)
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        List<Ingredient> ingredients = new ArrayList<>();
        for (Integer index : recipeDto.ingredients()) {
            Ingredient ingredient = ingredientRepository.getIngredient(index);
            if (ingredient == null)
                return new SuccessMessageDto(false, "Ingredient with this Id not found");
            ingredients.add(ingredient);
        }
        Recipe recipe = new Recipe(recipeDto.name(), recipeDto.time(), ingredients, recipeDto.steps());
        recipeRepository.addRecipe(recipe);
        return new SuccessMessageDto(true, "Recipe added successfully");
    }


}
