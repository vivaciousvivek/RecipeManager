import { Component } from '@angular/core';
import { Recipe } from '../shared/recipe.model';
import { Router } from '@angular/router';
import { RecipeService } from '../shared/recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent {
  private recipe: Recipe = {
    id: null,
    name: '',
    ingredient1: '',
    ingredient2: '',
    ingredient3: '',
    ingredient4: ''
  };
  private recipeName: any;

  constructor(private router: Router, private recipeService: RecipeService) {
    this.recipeService.getRecipeName().subscribe(data => this.recipeName = data);

    console.log(`getRecipeDetails :: Get recipe detail of recipeName: ${this.recipeName}`);

    if (this.recipeName && this.recipeName != 0) {
      this.recipeService.getRecipeDetails(this.recipeName).subscribe(data => {
        this.recipe = data;
        console.log(this.recipe);
      });
    } else {
      this.router.navigate(['']);
    }
  }
}
