package pro.sky.recipesbook.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipesbook.services.FileIngredientService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileIngredientServiceImpl implements FileIngredientService {

    @Value("${path.to.data.file}")
    private String filePath;
    @Value("${name.of.data.file.ingredients}")
    private String fileIngredientsName;

    @Override
    public void createFile() {
        if (Files.notExists(Path.of(filePath, fileIngredientsName))) {
            try {
                Files.createFile(Path.of(filePath, fileIngredientsName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void writeToFile(String json) {
        try {
            Files.writeString(Path.of(filePath, fileIngredientsName), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(filePath, fileIngredientsName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
