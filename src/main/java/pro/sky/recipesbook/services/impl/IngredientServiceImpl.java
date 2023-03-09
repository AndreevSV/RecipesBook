package pro.sky.recipesbook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.repository.IngredientRepository;
import pro.sky.recipesbook.services.FileIngredientService;
import pro.sky.recipesbook.services.IngredientService;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    private final FileIngredientService fileIngredientService;

    public static Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public Object getIngredient(Integer id) {
        readFromFile();
        Ingredient ingredient = ingredientRepository.getIngredient(id);
        if (ingredient != null) {
            return ingredient;
        }
        return new SuccessMessageDto(false, "Ingredient with this Id not found");
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        readFromFile();
        return ingredientRepository.getAllIngredients();
    }

    @Override
    public SuccessMessageDto addIngredient(IngredientDto ingredientDto) {
        if (ingredientDto.name() == null || ingredientDto.amount() <= 0 || ingredientDto.unit() == null) {
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        Ingredient ingredient = new Ingredient(ingredientDto.name(), ingredientDto.amount(), ingredientDto.unit());
        ingredientRepository.addIngredient(ingredient);
        saveToFile();
        return new SuccessMessageDto(true, "Ingredient added successfully");
    }

    @Override
    public SuccessMessageDto editIngredient(Integer id, IngredientDto ingredientDto) {
        if (ingredientDto.name() != null && ingredientDto.amount() > 0 && ingredientDto.unit() != null) {
            Ingredient ingredient = new Ingredient(ingredientDto.name(), ingredientDto.amount(), ingredientDto.unit());
            saveToFile();
            if (ingredientRepository.putIngredient(id, ingredient)) {
                return new SuccessMessageDto(true, "Ingredient edited successfully");
            }
        }
        return new SuccessMessageDto(false, "Incorrect data format");
    }

    @Override
    public SuccessMessageDto deleteIngredient(Integer id) {
        if (ingredientRepository.deleteIngredient(id)) {
            return new SuccessMessageDto(true, "Ingredient successfully deleted");
        }
        return new SuccessMessageDto(false, "Ingredient with this Id not found");
    }

    @Override
    public SuccessMessageDto deleteAllIngredients() {
        if (ingredientRepository.deleteAllIngredients()) {
            return new SuccessMessageDto(true, "All ingredients successfully deleted");
        }
        return new SuccessMessageDto(false, "Something get wrong");
    }

    private void saveToFile() {
        fileIngredientService.createFile();
        String string = null;
        try {
            string = new ObjectMapper().writeValueAsString(ingredientRepository);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        fileIngredientService.writeToFile(string);
    }


    private void readFromFile() {
        try {
            String json = fileIngredientService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
