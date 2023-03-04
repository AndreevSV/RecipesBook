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

    @GetMapping
    public ResponseEntity<?> getAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok().body(recipeService.addRecipe(recipeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRecipe(@PathVariable Integer id, @RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok().body(recipeService.editRecipe(id, recipeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recipeService.deleteRecipe(id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllRecipes() {
        return ResponseEntity.ok().body(recipeService.deleteAllRecipes());
    }
}
