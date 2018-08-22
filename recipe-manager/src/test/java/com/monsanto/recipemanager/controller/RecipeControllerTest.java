package com.monsanto.recipemanager.controller;

import com.monsanto.recipemanager.domain.Recipe;
import com.monsanto.recipemanager.repository.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-22 16:06:01)
 */
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = RecipeController.class)
public class RecipeControllerTest {

  @Autowired private WebTestClient webTestClient;

  @MockBean private RecipeRepository recipeRepository;

  private static final String baseUrl = "/api/recipe-manager";

  @Test
  public void getRecipeNames_ShouldBeOk() {
    webTestClient.get().uri(baseUrl + "/names").exchange().expectStatus().isOk();
  }

  @Test
  public void getRecipeNames_ShouldBe404() {
    webTestClient.get().uri(baseUrl + "/names/hello").exchange().expectStatus().isNotFound();
  }

  @Test
  public void getRecipeDetails_ShouldBeNotFound() {
    webTestClient
        .get()
        .uri(baseUrl + "{recipeName}", "Recipe 11")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  public void getRecipeDetails_ShouldBe404() {
    webTestClient
        .get()
        .uri(baseUrl)
        .attribute("recipeName", "Recipe 11")
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void addRecipe_ShouldBeIsOk() {
    webTestClient
        .post()
        .uri(baseUrl + "/addRecipe")
        .syncBody(new Recipe("Recipe 10", "New", "New", "New", "New"))
        .exchange()
        .expectStatus()
        .isOk();
  }
}
