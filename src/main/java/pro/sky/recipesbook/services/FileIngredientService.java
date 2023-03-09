package pro.sky.recipesbook.services;

public interface FileIngredientService {

    void createFile();

    void writeToFile(String json);

    String readFromFile();
}
