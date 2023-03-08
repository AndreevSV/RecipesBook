package pro.sky.recipesbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesbook.dto.IngredientDto;
import pro.sky.recipesbook.services.IngredientService;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с ингридиентами",
        description = "Позволяет работать с ингридиентами: добавить/изменнить/выести по номеру/вывести все/удалить по номеру/удалить все ингридиенты")
public class IngredientsController {

    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингридиента по его номеру",
            description = "Обязательный параметр в запросе номер Id ингридиента в url /{id}"
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
    public ResponseEntity<?> getIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ingredientService.getIngredient(id));
    }


    @Operation(summary = "Получение всех ингридиентов",
            description = "Обращение по url /ingredients"
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
    public ResponseEntity<?> getAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.getAllIngredients());
    }

    @Operation(summary = "Добавление нового ингридиента",
            description = "Ингридент передается в формате json в теле запроса"
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
    public ResponseEntity<?> addIngredient(@RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok().body(ingredientService.addIngredient(ingredientDto));
    }

    @Operation(summary = "Замена ингридиента с одним номером на другой",
            description = "Ингридент передается в формате json в теле запроса. Обязательные параметры в запросе номер Id " +
                    "ингридиента в url /{id} и тело с новым ингридиентом"
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
    public ResponseEntity<?> editIngredient(@PathVariable Integer id, @RequestBody IngredientDto ingredientDto) {
        return ResponseEntity.ok().body(ingredientService.editIngredient(id, ingredientDto));
    }

    @Operation(summary = "Удаление ингридиента с конкретным Id",
            description = "Обязательный параметр в запросе номер Id ингридиента в url /{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ingredientService.deleteIngredient(id));
    }

    @Operation(summary = "Удаление всех записанных ингридиентов",
            description = "Delete запрос по url /ingredients"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос отработан корректно")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.deleteAllIngredients());
    }
}
