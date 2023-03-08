package pro.sky.recipesbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Первый контроллер", description = "Показывает, что приложение запущено, а также информацию о приложении")
public class FirstController {

    @Operation(summary = "При заходе на localhost:8080 выводит, что приложение запущено")
    @GetMapping
    public String startApp() {
        return "Приложение запущено";
    }

    @Operation(summary = "При заходе на localhost:8080/info выводит информацию о приложении и разработчике")
    @GetMapping("/info")
    public String studentInfo() {
        return """
                Имя ученика: Андреев Сергей<br>
                Название проекта: Книга рецептов<br>
                Дата создания проекта: 13.01.2023<br>
                Описание проекта: Книга рецептов должна стать веб-приложением, которая по названию ингридиента выдает необходимый рецепт""";
    }
}
