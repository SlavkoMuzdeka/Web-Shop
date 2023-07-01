import { Component } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../home/home/services/product.service';
import { LoginService } from '../auth/services/login.service';

@Component({
  selector: 'app-finished-products',
  templateUrl: './finished-products.component.html',
  styleUrls: ['./finished-products.component.css'],
})
export class FinishedProductsComponent {
  private _products?: Product[];

  constructor(
    private productService: ProductService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.productService
      .getAllFinishedProducts(this.loginService.activeUser?.id)
      .subscribe((products: any) => {
        this._products = products;
      });
  }

  get products() {
    return this._products;
  }
}
