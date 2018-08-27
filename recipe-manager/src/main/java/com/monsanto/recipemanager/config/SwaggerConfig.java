package com.monsanto.recipemanager.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Run this application and go to /swagger-ui.html endpoint to access swagger documentation.
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-08-27 20:42:45)
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(paths())
        .build();
  }

  /**
   * Description about api
   *
   * @return
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Swagger Application APIs")
        .description("This page list all the REST APIs of this application")
        .version("1.0-SNAPSHOT")
        .build();
  }

  /**
   * Show only those APIs that matches the given predicates.
   *
   * <p>Match All paths except errors
   *
   * @return
   */
  private Predicate<String> paths() {
    return Predicates.and(
        PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error.*")));
  }
}
