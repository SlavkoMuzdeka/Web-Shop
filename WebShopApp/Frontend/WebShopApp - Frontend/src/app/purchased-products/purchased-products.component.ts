import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../home/home/services/product.service';
import { LoginService } from '../auth/services/login.service';

@Component({
  selector: 'app-purchased-products',
  templateUrl: './purchased-products.component.html',
  styleUrls: ['./purchased-products.component.css'],
})
export class PurchasedProductsComponent implements OnInit{
  private _products?: Product[];

  constructor(
    private productService: ProductService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.productService.getAllPurchasedProducts(this.loginService.activeUser?.id).subscribe((products: any) => {
      this._products = products;
    });
  }

  get products() {
    return this._products;
  }
}
