import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Recipe } from './recipe.model';

const apiUrl = environment.baseApiUrl + '/recipe-manager';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private recipeSubject = new BehaviorSubject<string>(null);

  constructor(private http: HttpClient) { 
    
  }

  getRecipesName(): Observable<any> {
      return this.http.get(`${apiUrl}/names`);
  }

  getRecipeDetails(recipeName: string): Observable<Recipe> {
    return this.http.get<Recipe>(`${apiUrl}/${recipeName}`);
  }

  saveRecipe(recipe: Recipe):any{
    console.log('saving recipe: ' + JSON.stringify(recipe));
    return this.http.post<Recipe>(`${apiUrl}/addRecipe`, recipe);
  }

  setRecipeName(recipeName: string) {
    this.recipeSubject.next(recipeName);
  }

  getRecipeName() {
    return this.recipeSubject.asObservable();
  }

  public get $recipeSubject(): BehaviorSubject<string> {
    return this.recipeSubject;
  }

  public set $recipeSubject(value: BehaviorSubject<string>) {
    this.recipeSubject;
  }
}
