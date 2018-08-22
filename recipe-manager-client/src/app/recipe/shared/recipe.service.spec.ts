import { TestBed, inject } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { RecipeService } from './recipe.service';
import { HttpEvent, HttpEventType } from '@angular/common/http';

describe('RecipeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RecipeService]
    });
  });

  it('should be created', inject([RecipeService], (service: RecipeService) => {
    expect(service).toBeTruthy();
  }));

  it('should return recipe names',inject( [RecipeService],(service: RecipeService) =>{
  service.getRecipesName().subscribe((event: HttpEvent<any>) => {
    switch (event.type) {
      case HttpEventType.Response:
        expect(event.status).toEqual(200);
    }
  });
  }));

  it('should return recipe details',inject( [RecipeService],(service: RecipeService) =>{
    service.getRecipeName().subscribe(responce => {
      expect(responce).not.toBeUndefined;
    });
    }));

    it('should return recipe after saving',inject( [RecipeService],(service: RecipeService) =>{
      service.saveRecipe({'id': null,name:'test',ingredient1:'testIngredients',ingredient2:null,ingredient3:null,ingredient4:null}).subscribe(responce => {
         expect(responce).not.toBeNull;
      });
      }));
});
