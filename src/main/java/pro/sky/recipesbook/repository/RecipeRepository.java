package pro.sky.recipesbook.repository;

import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RecipeRepository {

    private final Map<Integer, Recipe> recipes = new HashMap<>();

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

}
