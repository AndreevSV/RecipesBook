package pro.sky.recipesbook.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.repository.IngredientRepository;
import pro.sky.recipesbook.services.IngredientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Object getIngredient(Integer id) {
        Ingredient ingredient = ingredientRepository.getIngredient(id);
        if (ingredient != null) {
            return ingredient;
        }
        return new SuccessMessageDto(false, "Ingredient with this Id not found");
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.getAllIngredients();
    }

    @Override
    public SuccessMessageDto addIngredient(IngredientDto ingredientDto) {
        if (StringUtils.isEmpty(ingredientDto.name()) || ingredientDto.amount() <= 0 || StringUtils.isEmpty(ingredientDto.unit())) {
            return new SuccessMessageDto(false, "Incorrect data format");
        }
        Ingredient ingredient = new Ingredient(ingredientDto.name(), ingredientDto.amount(), ingredientDto.unit());
        ingredientRepository.addIngredient(ingredient);
        return new SuccessMessageDto(true, "Ingredient added successfully");
    }

    @Override
    public SuccessMessageDto editIngredient(Integer id, IngredientDto ingredientDto) {
        if (StringUtils.isEmpty(ingredientDto.name()) && ingredientDto.amount() > 0 && StringUtils.isEmpty(ingredientDto.unit())) {
            Ingredient ingredient = new Ingredient(ingredientDto.name(), ingredientDto.amount(), ingredientDto.unit());
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


}
