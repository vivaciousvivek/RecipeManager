import { Component, ViewContainerRef } from '@angular/core';
import { RecipeService } from './recipe/shared/recipe.service';
import { Router, NavigationEnd } from '@angular/router';
import { ToastsManager } from 'ng6-toastr/ng2-toastr';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private title = 'Recipe Manager';
  private recipeNames: string[] = [];

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private recipeService: RecipeService,
    public toastr: ToastsManager, vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);

    this.router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        this.recipeService.getRecipesName().subscribe(data => {
          this.recipeNames = data;
          console.log(this.recipeNames);
        });
      }
    });
  }

  getRecipeDetails(recipeName: string) {
    console.log(`getRecipeDetails :: Get recipe detail of recipeId: ${recipeName}`);
    this.recipeService.setRecipeName(recipeName);
    this.router.navigate(['']).then(() => this.router.navigate(['recipe-list']));
  }
}
