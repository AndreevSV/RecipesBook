package pro.sky.recipesbook.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.model.Recipe;
import pro.sky.recipesbook.services.FileRecipeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RecipeRepository {

    private final Map<Integer, Recipe> recipes = new HashMap<>();

    private final FileRecipeService fileRecipeService;

    public void addRecipe(Recipe recipe) {
        recipes.put(recipes.size() + 1, recipe);
    }

    public Recipe getRecipe(Integer id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        }
        return null;
    }

    public List<Recipe> getAllRecipes() {
            return new ArrayList<>(recipes.values());
    }

    public Boolean putRecipe(Integer id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return true;
        }
        return false;
    }

    public Boolean deleteRecipe(Integer id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            return true;
        }
        return false;
    }

    public Boolean deleteAllRecipes() {
       recipes.clear();
       return true;
    }

    public void saveToFile() {
        fileRecipeService.createFile();
        String string = null;
        try {
            string = new ObjectMapper().writeValueAsString(recipes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        fileRecipeService.writeToFile(string);
    }

    public void readFromFile() {
        try {
            String json = fileRecipeService.readFromFile();
            new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
