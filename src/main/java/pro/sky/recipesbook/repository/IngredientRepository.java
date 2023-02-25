package pro.sky.recipesbook.repository;

import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class IngredientRepository {
    private final Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredients.size()+1, ingredient);
    }

    public Ingredient getIngredient(Integer id) {
        if (ingredients.containsKey(id))
            return ingredients.get(id);
        return null;
    }
}
