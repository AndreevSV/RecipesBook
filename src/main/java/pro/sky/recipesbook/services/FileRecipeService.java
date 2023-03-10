package pro.sky.recipesbook.services;

public interface FileRecipeService {
    void createFile();

    void writeToFile(String json);

    String readFromFile();
}
