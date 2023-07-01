import { LoginService } from 'src/app/auth/services/login.service';
import { Component, Input } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../home/home/services/product.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
})
export class ProductCardComponent {
  @Input() public products?: Product[];
  @Input() public title?: string;

  constructor(
    private productService: ProductService,
    private snackBar: MatSnackBar
  ) {}

  isFinished(product_id: number) {
    const product = this.products?.find((p) => p.id === product_id);
    if (product?.isFinished) return false;
    return true;
  }

  isPurchasedProduct() {
    if (this.title == 'Purchased Products') {
      return true;
    }
    return false;
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe((result: any) => {
      if (result.status_code == 200) {
        const index =
          this.products?.findIndex((product) => product.id === id) ?? -1;
        if (index !== -1) {
          this.products?.splice(index, 1);
          this.snackBar.open('Product deleted successfully', undefined, {
            duration: 2000,
          });
        }
      }
    });
  }

  finishProduct(id: number) {
    this.productService.finishProduct(id).subscribe((result: any) => {
      if (result.status_code == 200) {
        const index =
          this.products?.findIndex((product) => product.id === id) ?? -1;
        if (index !== -1) {
          this.products?.splice(index, 1);
          this.snackBar.open('Product finished successfully', undefined, {
            duration: 2000,
          });
        }
      }
    });
  }
}
