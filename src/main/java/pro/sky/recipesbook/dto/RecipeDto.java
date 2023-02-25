package pro.sky.recipesbook.dto;

import java.util.List;

public record RecipeDto(String name, int time, List<Integer> ingredients, List<String> steps) {

}
