package pro.sky.recipesbook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.services.RecipeService;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok().body(recipeService.addRecipe(recipeDto));
    }

}
