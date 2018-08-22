package com.monsanto.recipemanager.repository;

import com.monsanto.recipemanager.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-21 20:42:49)
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
  Optional<Recipe> findByName(String name);
}
