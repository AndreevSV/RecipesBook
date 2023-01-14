package pro.sky.recipesbook.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {


    @GetMapping
    public String startApp() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String studentInfo() {
        return """
                Имя ученика: Андреев Сергей<br>
                Название проекта: Книга рецептов<br>
                Дата создания проекта: 13.01.2023<br>
                Описание проекта: Книга рецептов должна стать веб-приложением, которая по названию ингридиента выдает необходимый рецепт""";
    }




}
