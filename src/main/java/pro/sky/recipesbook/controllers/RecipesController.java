package pro.sky.recipesbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesbook.dto.RecipeDto;
import pro.sky.recipesbook.services.RecipeService;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с рецептами",
        description = "Позволяет работать с рецептами: добавить/изменнить/выести по номеру/вывести все/удалить по номеру/удалить все рецепты")
public class RecipesController {

    private final RecipeService recipeService;

    @Operation(summary = "Получение рецепта по его номеру",
            description = "Обязательный параметр в запросе номер Id рецепта в url /{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }

    @Operation(summary = "Получение всех рецептов",
            description = "Обращение по url /recipes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<?> getAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @Operation(summary = "Добавление нового рецепта",
            description = "Рецепт передается в формате json в теле запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok().body(recipeService.addRecipe(recipeDto));
    }

    @Operation(summary = "Замена рецепта с одним номером на другой",
            description = "Рецепт передается в формате json в теле запроса. Обязательные параметры в запросе номер Id " +
                    "рецепта в url /{id} и тело с новым рецептом"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> editRecipe(@PathVariable Integer id, @RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok().body(recipeService.editRecipe(id, recipeDto));
    }

    @Operation(summary = "Удаление рецепта с конкретным Id",
            description = "Обязательный параметр в запросе номер Id рецепта в url /{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recipeService.deleteRecipe(id));
    }

    @Operation(summary = "Удаление всех записанных рецептов",
            description = "Delete запрос по url /recipes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteAllRecipes() {
        return ResponseEntity.ok().body(recipeService.deleteAllRecipes());
    }
}
