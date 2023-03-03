package pro.sky.recipesbook.repository;

import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Ingredient;

import java.util.*;

@Repository
public class IngredientRepository {
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredients.size() + 1, ingredient);
    }

    public Ingredient getIngredient(Integer id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        }
        return null;
    }

    public List<Ingredient> getAllIngredients() {
            return new ArrayList<>(ingredients.values());
    }

    public Boolean putIngredient(Integer id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return true;
        }
        return false;
    }

    public Boolean deleteIngredient(Integer id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }

    public Boolean deleteAllIngredients() {
        ingredients.clear();
        return true;
    }
}
