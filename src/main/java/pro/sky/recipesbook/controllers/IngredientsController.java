package pro.sky.recipesbook.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.services.IngredientService;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientsController {

    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ingredientService.getIngredient(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.getAllIngredients());
    }

    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok().body(ingredientService.addIngredient(ingredientDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editIngredient(@PathVariable Integer id, @RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok().body(ingredientService.editIngredient(id, ingredientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ingredientService.deleteIngredient(id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.deleteAllIngredients());
    }
}
