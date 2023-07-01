import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { UserComponent } from './user/user.component';
import { GuardService } from './auth/services/guard.service';
import { PurchasedProductsComponent } from './purchased-products/purchased-products.component';
import { ActiveProductsComponent } from './active-products/active-products.component';
import { FinishedProductsComponent } from './finished-products/finished-products.component';
import { NewProductComponent } from './new-product/new-product.component';
import { EnterPinComponent } from './auth/enter-pin/enter-pin.component';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () =>
      import('./auth/auth.module').then((mod) => mod.AuthModule),
  },
  {
    path: '',
    loadChildren: () =>
      import('./home/home.module').then((mod) => mod.HomeModule),
  },
  { path: 'products/:id', component: ProductComponent },
  {
    path: 'accountData',
    component: UserComponent,
    canActivate: [GuardService],
  },
  {
    path: 'purchasedProducts/:userId',
    component: PurchasedProductsComponent,
    canActivate: [GuardService],
  },
  {
    path: 'activeProducts/:userId',
    component: ActiveProductsComponent,
    canActivate: [GuardService],
  },
  {
    path: 'finishedProducts/:userId',
    component: FinishedProductsComponent,
    canActivate: [GuardService],
  },
  {
    path: 'createProduct',
    component: NewProductComponent,
    canActivate: [GuardService],
  },
  {
    path: 'enter-pin',
    component: EnterPinComponent,
    canActivate: [GuardService],
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
