import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeService } from '../shared/recipe.service';
import { Recipe } from '../shared/recipe.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastsManager } from 'ng6-toastr';

@Component({
  selector: 'app-new-recipe',
  templateUrl: './new-recipe.component.html',
  styleUrls: ['./new-recipe.component.css']
})
export class NewRecipeComponent implements OnInit {

  private recipe: Recipe;
  private myform: FormGroup;
  private name: FormControl;
  private ingredient1: FormControl;
  private ingredient2: FormControl;
  private ingredient3: FormControl;
  private ingredient4: FormControl;

  constructor(private router: Router, private recipeService: RecipeService, public toastr: ToastsManager) { }

  ngOnInit() {
    this.createFormControl();
    this.createForm();
  }

  createFormControl() {
    this.name = new FormControl('', Validators.required);
    this.ingredient1 = new FormControl('', Validators.required);
    this.ingredient2 = new FormControl('', Validators.required);
    this.ingredient3 = new FormControl('', Validators.required);
    this.ingredient4 = new FormControl('', Validators.required);
  }

  createForm() {
    this.myform = new FormGroup({
      name: this.name,
      ingredient1: this.ingredient1,
      ingredient2: this.ingredient2,
      ingredient3: this.ingredient3,
      ingredient4: this.ingredient4
    });
  }

  onSubmit() {
    if (this.name != null && (this.ingredient1.value || this.ingredient2.value || this.ingredient3.value || this.ingredient4.value)) {
      console.log('Recipe is Submitted!');
      console.log(this.myform.value);
      this.recipe = {
        id: null,
        name: this.name.value,
        ingredient1: this.ingredient1.value,
        ingredient2: this.ingredient2.value,
        ingredient3: this.ingredient3.value,
        ingredient4: this.ingredient4.value
      };

      this.recipeService.saveRecipe(this.recipe).subscribe((responce) => {
        this.toastr.success('Recipe Added Successfully!');
        this.router.navigate(['']).then(() => this.router.navigate(['recipe-list']));
      }, (err) => {
        this.toastr.error(err.status + ' : ' + err.message);
      });

      this.myform.reset();
    } else {
      this.toastr.info('Please fill recipe name and atleast one ingredient!');
    }
  }
}
