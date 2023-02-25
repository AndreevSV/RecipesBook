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

    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok().body(ingredientService.addIngredient(ingredientDto));
    }
}
