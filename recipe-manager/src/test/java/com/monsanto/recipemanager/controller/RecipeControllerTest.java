package com.monsanto.recipemanager.controller;

import com.monsanto.recipemanager.RecipeManagerApplication;
import com.monsanto.recipemanager.repository.RecipeRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-22 16:06:01)
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RecipeManagerApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipeControllerTest {

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext webApplicationContext;

  private WebTestClient webTestClient;

  @Autowired private RecipeRepository recipeRepository;

  private static final String baseUrl = "/api/recipe-manager";

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void getRecipeNames_ShouldBeOk() throws Exception {
    mockMvc
        .perform(get(baseUrl + "/names").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0]", is("Recipe 1")))
        .andExpect(jsonPath("$[1]", is("Recipe 2")))
        .andExpect(jsonPath("$[2]", is("Recipe 3")))
        .andExpect(jsonPath("$[3]", is("Recipe 4")))
        .andExpect(jsonPath("$[4]", is("Recipe 5")))
        .andDo(print());
  }

  @Test
  public void getRecipeNames_ShouldBe404() throws Exception {
    mockMvc
        .perform(get(baseUrl + "/names/hello").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  public void getRecipeDetails_ShouldBeOk() throws Exception {
    mockMvc
        .perform(get(baseUrl + "/Recipe 1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Recipe 1")))
        .andExpect(jsonPath("$.ingredient1", is("Sugar")))
        .andExpect(jsonPath("$.ingredient2", is("MS Basal Salt")))
        .andExpect(jsonPath("$.ingredient3", is("Proline")))
        .andExpect(jsonPath("$.ingredient4", is("NZ Amine")))
        .andDo(print());
  }

  @Test
  public void getRecipeDetails_ShouldBe404() throws Exception {
    mockMvc
        .perform(get(baseUrl + "/Recipe 11").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  public void addRecipe_ShouldBeIsOk() throws Exception {
    mockMvc
        .perform(
            post(baseUrl + "/addRecipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{ \"name\" : \"Recipe 10\", \"ingredient1\" : \"Ingredient1\", \"ingredient2\" : \"Ingredient2\", \"ingredient3\" : \"Ingredient3\", \"ingredient4\" : \"Ingredient4\" }")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.ingredient1").exists())
        .andExpect(jsonPath("$.ingredient2").exists())
        .andExpect(jsonPath("$.ingredient3").exists())
        .andExpect(jsonPath("$.ingredient4").exists())
        .andExpect(jsonPath("$.name").value("Recipe 10"))
        .andExpect(jsonPath("$.ingredient1").value("Ingredient1"))
        .andExpect(jsonPath("$.ingredient2").value("Ingredient2"))
        .andExpect(jsonPath("$.ingredient3").value("Ingredient3"))
        .andExpect(jsonPath("$.ingredient4").value("Ingredient4"))
        .andDo(print());
  }

  @Test
  public void addRecipe_ShouldBe() throws Exception {
    mockMvc
        .perform(
            post(baseUrl + "/addRecipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{ \"name\" : \"Recipe 10\", \"ingredient1\" : \"Oil\", \"ingredient2\" : \"MS Basal Salt\", \"ingredient3\" : \"Onion\", \"ingredient4\" : \"Cucumber\" }")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.ingredient1").exists())
        .andExpect(jsonPath("$.ingredient2").exists())
        .andExpect(jsonPath("$.ingredient3").exists())
        .andExpect(jsonPath("$.ingredient4").exists())
        .andExpect(jsonPath("$.name").value("Recipe 10"))
        .andExpect(jsonPath("$.ingredient1").value("Oil"))
        .andExpect(jsonPath("$.ingredient2").value("MS Basal Salt"))
        .andExpect(jsonPath("$.ingredient3").value("Onion"))
        .andExpect(jsonPath("$.ingredient4").value("Cucumber"))
        .andDo(print());
  }
}
