package pro.sky.recipesbook.services.impl;

import org.springframework.beans.factory.annotation.Value;
import pro.sky.recipesbook.services.FileRecipeService;

public class FileRecipeServiceImpl implements FileRecipeService {

    @Value("${path.to.data.file}")
    private String filePath;
    @Value("${name.of.data.file.ingredients}")
    private String fileIngredientsName;
    @Value("${name.of.data.file.recipes}")
    private String fileRecipeName;


}
