package com.monsanto.recipemanager;

import com.monsanto.recipemanager.domain.Recipe;
import com.monsanto.recipemanager.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-21 20:26:45)
 */
@SpringBootApplication
public class RecipeManagerApplication implements CommandLineRunner {

  private RecipeRepository recipeRepository;

  public RecipeManagerApplication(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  /**
   * This override method of CommandLineRunner will be loaded after the application context is
   * loaded and just before the run method will execute
   *
   * @param args
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    // insert items into Recipe table
    Stream.of(
            new Recipe("Recipe 1", "Sugar", "MS Basal Salt", "Proline", "NZ Amine"),
            new Recipe("Recipe 2", "Coffee", "Sugar", "Water", "Milk"),
            new Recipe("Recipe 3", "Tea", "Milk", "Sugar", "Water"),
            new Recipe("Recipe 4", "Milk", "Sugar", "Corn", "Mint"),
            new Recipe("Recipe 5", "Oil", "MS Basal Salt", "Onion", "Cucumber"))
        .forEach(recipe -> recipeRepository.save(new Recipe(recipe)));

    // print all items inserted into Item table.
    recipeRepository.findAll().forEach(System.out::println);
  }

  public static void main(String[] args) {
    SpringApplication.run(RecipeManagerApplication.class, args);
  }
}
