import { Component, OnInit } from '@angular/core';
import { ProductService } from '../home/home/services/product.service';
import { Product } from '../model/product.model';
import { LoginService } from '../auth/services/login.service';

@Component({
  selector: 'app-active-products',
  templateUrl: './active-products.component.html',
  styleUrls: ['./active-products.component.css'],
})
export class ActiveProductsComponent implements OnInit {
  private _products?: Product[];

  constructor(
    private productService: ProductService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.productService
      .getAllActiveProducts(this.loginService.activeUser?.id)
      .subscribe((products: any) => {
        this._products = products;
      });
  }

  get products() {
    return this._products;
  }
}
