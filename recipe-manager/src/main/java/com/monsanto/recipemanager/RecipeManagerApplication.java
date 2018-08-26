package com.monsanto.recipemanager;

import com.monsanto.recipemanager.repository.RecipeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-21 20:26:45)
 */
@SpringBootApplication
public class RecipeManagerApplication {

  private RecipeRepository recipeRepository;

  public RecipeManagerApplication(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(RecipeManagerApplication.class, args);
  }
}
