package com.monsanto.recipemanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-21 20:42:53)
 */
@Entity
public class Recipe {
  @Id @GeneratedValue private int id;

  @Column(unique = true)
  private String name;

  private String ingredient1;

  private String ingredient2;

  private String ingredient3;

  private String ingredient4;

  public Recipe() {}

  public Recipe(Recipe recipe) {
    this(
        recipe.name,
        recipe.ingredient1,
        recipe.ingredient2,
        recipe.ingredient3,
        recipe.ingredient4);
  }

  public Recipe(
      String name, String ingredient1, String ingredient2, String ingredient3, String ingredient4) {
    this.name = name;
    this.ingredient1 = ingredient1;
    this.ingredient2 = ingredient2;
    this.ingredient3 = ingredient3;
    this.ingredient4 = ingredient4;
  }

  public int getId() {
    return id;
  }

  public Recipe setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Recipe setName(String name) {
    this.name = name;
    return this;
  }

  public String getIngredient1() {
    return ingredient1;
  }

  public Recipe setIngredient1(String ingredient1) {
    this.ingredient1 = ingredient1;
    return this;
  }

  public String getIngredient2() {
    return ingredient2;
  }

  public Recipe setIngredient2(String ingredient2) {
    this.ingredient2 = ingredient2;
    return this;
  }

  public String getIngredient3() {
    return ingredient3;
  }

  public Recipe setIngredient3(String ingredient3) {
    this.ingredient3 = ingredient3;
    return this;
  }

  public String getIngredient4() {
    return ingredient4;
  }

  public Recipe setIngredient4(String ingredient4) {
    this.ingredient4 = ingredient4;
    return this;
  }

  @Override
  public String toString() {
    return "Recipe{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", ingredient1='"
        + ingredient1
        + '\''
        + ", ingredient2='"
        + ingredient2
        + '\''
        + ", ingredient3='"
        + ingredient3
        + '\''
        + ", ingredient4='"
        + ingredient4
        + '\''
        + '}';
  }
}
