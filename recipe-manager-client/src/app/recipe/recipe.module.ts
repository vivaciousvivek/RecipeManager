import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecipeRoutingModule } from './recipe-routing.module';
import { NewRecipeComponent } from './new-recipe/new-recipe.component';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { RecipeService } from './shared/recipe.service';
import { WelcomeComponent } from './welcome/welcome.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    RecipeRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    NewRecipeComponent,
    RecipeListComponent,
    WelcomeComponent
  ],
  providers: [
    RecipeService
  ]
})
export class RecipeModule { }
