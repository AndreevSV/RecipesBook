package pro.sky.recipesbook.repository;

import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Recipe;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class RecipeRepository {

    private final Map<Integer, Recipe> recipes = new LinkedHashMap<>();

    public void addRecipe(Recipe recipe) {
        recipes.put(recipes.size()+1, recipe);
    }

    public Recipe getRecipe(Integer id) {
        if (recipes.containsKey(id))
            return recipes.get(id);
        return null;
    }

}
