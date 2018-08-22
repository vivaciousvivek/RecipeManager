import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRecipeComponent } from './new-recipe.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ToastsManager, ToastModule } from 'ng6-toastr';

describe('NewRecipeComponent', () => {
  let component: NewRecipeComponent;
  let fixture: ComponentFixture<NewRecipeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports:[ReactiveFormsModule,RouterTestingModule,HttpClientTestingModule,ToastModule.forRoot()],
      declarations: [ NewRecipeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Should Add Record', () =>{
    expect()
  });
});
