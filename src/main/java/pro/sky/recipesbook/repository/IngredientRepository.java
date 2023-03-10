package pro.sky.recipesbook.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.services.FileIngredientService;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class IngredientRepository {
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();

    private final FileIngredientService fileIngredientService;

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

    public void saveToFile() {
        fileIngredientService.createFile();
        String string = null;
        try {
            string = new ObjectMapper().writeValueAsString(ingredients);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        fileIngredientService.writeToFile(string);
    }

    public void readFromFile() {
        try {
            String json = fileIngredientService.readFromFile();
            new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
