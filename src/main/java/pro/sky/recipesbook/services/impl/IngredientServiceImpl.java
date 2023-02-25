package pro.sky.recipesbook.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.dto.SuccessMessageDto;
import pro.sky.recipesbook.model.Ingredient;
import pro.sky.recipesbook.repository.IngredientRepository;
import pro.sky.recipesbook.services.IngredientService;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Object getIngredient(Integer id) {
        Ingredient ingredient = ingredientRepository.getIngredient(id);
        if (ingredient != null)
        return ingredient;
        return new SuccessMessageDto(false, "Ingredient with this Id not found");
    }

    @Override
    public SuccessMessageDto addIngredient(IngredientDto ingredientDto) {
        if (ingredientDto.name() == null || ingredientDto.amount() <= 0 || ingredientDto.unit() == null)
            return new SuccessMessageDto(false, "Incorrect data format");
        Ingredient ingredient = new Ingredient(ingredientDto.name(), ingredientDto.amount(), ingredientDto.unit());
        ingredientRepository.addIngredient(ingredient);
        return new SuccessMessageDto(true, "Ingredient added successfully");
    }
}
