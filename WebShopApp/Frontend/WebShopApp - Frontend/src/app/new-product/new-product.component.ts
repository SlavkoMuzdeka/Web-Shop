import { LoginService } from 'src/app/auth/services/login.service';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ProductService } from '../home/home/services/product.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from '../category/services/category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css'],
})
export class NewProductComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  private images: any[] = [];
  private _attributes?: any[];
  private _categoryId?: number;

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private snackBar: MatSnackBar,
    private categoryService: CategoryService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
      location: [null, Validators.required],
      contact: [null, Validators.required],
      type: [null],
      category: [null],
      attribute: [null],
    });
  }

  getErrorMessage(controlName: string): string {
    const control = this.form.get(controlName);
    if (control?.hasError('required')) {
      return 'You must enter a value';
    }
    return '';
  }

  createProduct() {
    if (this._categoryId) {
      const formData = this.form.value;
      const product = {
        title: formData.title,
        description: formData.description,
        price: formData.price,
        location: formData.location,
        contact: formData.contact,
        type: formData.type,
        images: this.images,
        attributes: this._attributes,
        seller: this.loginService.activeUser,
        category: this._categoryId
      };
      this.productService.createProduct(product).subscribe(
        (response: any) => {
          if(response.status_code == 201){
            this.snackBar.open(
              'Product has been created successfully!',
              undefined,
              {
                duration: 2000,
              }
            );
            this.router.navigate(['/']);
          }else {
            this.snackBar.open(
              'Product creation failed. Please try again.',
              undefined,
              {
                duration: 2000,
              }
            );
          }
        },
      );
    } else {
      this.snackBar.open('You have to select category!', undefined, {
        duration: 2000,
      });
    }
  }

  onFileSelected(event: any) {
    const files: File[] = event.target.files;
    let counter = 0;

    const decodeFile = (file: File) => {
      const reader = new FileReader();
      reader.onload = (event: any) => {
        const image = {
          img: event.target.result.split(',')[1],
        };
        this.images.push(image);
        counter++;
        if (counter < files.length) {
          decodeFile(files[counter]);
        }
      };
      reader.readAsDataURL(file);
    };

    decodeFile(files[counter]);
  }

  showAttributes(categoryId: number) {
    if (this._categoryId == categoryId) {
      this._attributes = undefined;
      this._categoryId = undefined;
    } else {
      this._categoryId = categoryId;
      this.categoryService
        .getCategoryAttributes(categoryId)
        .subscribe((attributes: any) => {
          this._attributes = attributes;
        });
    }
  }

  get attributes() {
    return this._attributes;
  }
}
