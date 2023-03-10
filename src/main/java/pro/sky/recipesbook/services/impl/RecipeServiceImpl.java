package pro.sky.recipesbook.services.impl;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.model.Recipe;
import pro.sky.recipesbook.repository.IngredientRepository;
import pro.sky.recipesbook.repository.RecipeRepository;
import pro.sky.recipesbook.services.RecipeService;

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
        if (recipe != null) {
            return recipe;
        }
        return new SuccessMessageDto(false, "Recipe with this Id not found");
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    @Override
    public SuccessMessageDto addRecipe(RecipeDto recipeDto) {
        if (StringUtils.isBlank(recipeDto.name()) || recipeDto.time() <= 0) {
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        for (String str : recipeDto.steps()) {
            if (StringUtils.isBlank(str)) {
                return new SuccessMessageDto(false, "Incorrect data format");
            }
        }
        List<Ingredient> ingredients = new ArrayList<>();
        List<Integer> listId = recipeDto.ingredients();
        for (Integer index : listId) {
            Ingredient ingredient = ingredientRepository.getIngredient(index);
            if (ingredient == null) {
                return new SuccessMessageDto(false, "Ingredient with this Id not found");
            }
            ingredients.add(ingredient);
        }
        Recipe recipe = new Recipe(recipeDto.name(), recipeDto.time(), ingredients, recipeDto.steps());
        recipeRepository.addRecipe(recipe);
        return new SuccessMessageDto(true, "Recipe added successfully");
    }

    @Override
    public SuccessMessageDto editRecipe(Integer id, RecipeDto recipeDto) {
        if (StringUtils.isBlank(recipeDto.name()) || recipeDto.time() <= 0) {
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        for (Integer index : recipeDto.ingredients()) {
            if (index == null) {
                return new SuccessMessageDto(false, "Incorrect data format");
            }
        }
        for (String str : recipeDto.steps()) {
            if (StringUtils.isBlank(str)) {
                return new SuccessMessageDto(false, "Incorrect data format");
            }
        }
        List<Ingredient> ingredients = new ArrayList<>();
        Recipe recipe = new Recipe(recipeDto.name(), recipeDto.time(), ingredients, recipeDto.steps());
        if (recipeRepository.putRecipe(id, recipe)) {
            return new SuccessMessageDto(true, "Editing recipe done successfully");
        }
        return new SuccessMessageDto(false, "Editing haven't been done");
    }

    @Override
    public SuccessMessageDto deleteRecipe(Integer id) {
        if (recipeRepository.deleteRecipe(id)) {
            return new SuccessMessageDto(true, "Recipe deleted successfully");
        }
        return new SuccessMessageDto(false, "Recipe with this Id not found");
    }

    @Override
    public SuccessMessageDto deleteAllRecipes() {
        if (recipeRepository.deleteAllRecipes()) {
            return new SuccessMessageDto(true, "All recipes have been deleted");
        }
        return new SuccessMessageDto(false, "Something get wrong");
    }
}
