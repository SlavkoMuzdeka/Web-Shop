import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryComponent } from '../category/category.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppModule } from '../app.module';
import { HeaderComponent } from '../header/header.component';


@NgModule({
  declarations: [
    HomeComponent,
    CategoryComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
  ],
  exports: [HeaderComponent, CategoryComponent],
})
export class HomeModule { }
