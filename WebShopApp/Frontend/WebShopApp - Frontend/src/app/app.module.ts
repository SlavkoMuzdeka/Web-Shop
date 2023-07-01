import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './app-material/app-material.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TypeOfPurchasingComponent } from './type-of-purchasing/type-of-purchasing.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UserComponent } from './user/user.component';
import { PurchasedProductsComponent } from './purchased-products/purchased-products.component';
import { HomeModule } from './home/home.module';
import { ActiveProductsComponent } from './active-products/active-products.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { FinishedProductsComponent } from './finished-products/finished-products.component';
import { UserSupportDialogComponent } from './user-support-dialog/user-support-dialog.component';
import { NewProductComponent } from './new-product/new-product.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    TypeOfPurchasingComponent,
    UserComponent,
    PurchasedProductsComponent,
    ActiveProductsComponent,
    ProductCardComponent,
    FinishedProductsComponent,
    UserSupportDialogComponent,
    NewProductComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    HttpClientModule,
    CommonModule,
    FontAwesomeModule,
    FormsModule,
    MatDialogModule,
    ReactiveFormsModule,
    HomeModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
