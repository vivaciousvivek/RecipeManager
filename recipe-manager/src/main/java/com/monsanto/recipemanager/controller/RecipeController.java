package com.monsanto.recipemanager.controller;

import com.monsanto.recipemanager.domain.Recipe;
import com.monsanto.recipemanager.repository.RecipeRepository;
import com.monsanto.recipemanager.utils.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-21 20:42:45)
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/recipe-manager")
public class RecipeController {

  private RecipeRepository recipeRepository;

  public RecipeController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping("/names")
  public List<String> getRecipeNames() {
    return recipeRepository.findAll().stream().map(Recipe::getName).collect(Collectors.toList());
  }

  @GetMapping("/{recipeName}")
  public Recipe getRecipeDetails(@PathVariable String recipeName) {
    return recipeRepository
        .findByName(recipeName)
        .orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipeName", recipeName));
  }

  @PostMapping("/addRecipe")
  public Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
    Optional<Recipe> oldRecipe = recipeRepository.findByName(recipe.getName());
    if (oldRecipe.isPresent()) recipeRepository.delete(oldRecipe.get());

    return recipeRepository.save(recipe);
  }
}
