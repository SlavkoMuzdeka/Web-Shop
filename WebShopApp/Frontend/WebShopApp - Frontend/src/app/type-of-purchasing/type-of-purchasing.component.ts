import { Component, Inject, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
import { ProductService } from '../home/home/services/product.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-type-of-purchasing',
  templateUrl: './type-of-purchasing.component.html',
  styleUrls: ['./type-of-purchasing.component.css'],
})
export class TypeOfPurchasingComponent implements OnInit {
  private _showCardInput: boolean = false;
  cardNumber: any = { text: '' };

  constructor(
    private dialogRef: MatDialogRef<TypeOfPurchasingComponent>,
    private productService: ProductService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {}

  withCard() {
    this._showCardInput = true;
  }

  payWithCard() {
    if (this.cardNumber.text != "" ) {
      this.sendRequest();
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

  withoutCard() {
    this._showCardInput = false;
    this.sendRequest();
  }

  sendRequest() {
    this.productService
      .buyProduct(this.data.id, this.data.product_id)
      .then(() => {
        this.dialogRef.close(true);
        this.snackBar.open(
          'Product has been purchased successfully!',
          undefined,
          {
            duration: 2000,
          }
        );
      })
      .catch(() => {
        this.dialogRef.close();
        this.snackBar.open(
          'Error occurred while purchasing product.',
          undefined,
          {
            duration: 2000,
          }
        );
      });
  }

  get showCardInput() {
    return this._showCardInput;
  }
}
