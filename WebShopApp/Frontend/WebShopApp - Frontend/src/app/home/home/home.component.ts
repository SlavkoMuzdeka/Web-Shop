import { LoginService } from './../../auth/services/login.service';
import { Component } from '@angular/core';
import { Product } from 'src/app/model/product.model';
import { ProductService } from './services/product.service';
import { PageEvent } from '@angular/material/paginator';
import { faAdd } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/category/services/category.service';
import { Attribute } from 'src/app/model/attribute.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  private _products?: Product[];
  private _attributes?: Attribute[];

  searchText: string = '';
  faAdd = faAdd;

  private _total: number = 0;
  private _categoryId?: number;
  private _filterData: boolean = false;
  private _searchData: boolean = false;
  private _pageIndex: number = 0;
  private _pageSize: number = 3;

  constructor(
    private productService: ProductService,
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this._pageIndex = +params['page'] || 0;
      this._pageSize = +params['size'] || 3;

      if (this._filterData) {
        this.getFilteredProducts(
          this._pageIndex,
          this._pageSize,
          this._categoryId
        );
      } else if(this._searchData){
        this.getFilteredProductsByName(this.pageIndex, this.pageSize, this.searchText);
      } else{
        this.updateUrl(this._pageIndex, this._pageSize);
        this.getAllProducts(this._pageIndex, this._pageSize);
      }
    });
  }

  updateUrl(pageIndex: number, pageSize: number) {
    const urlTree = this.router.createUrlTree([], {
      queryParams: { page: pageIndex, size: pageSize },
    });
    this.router.navigateByUrl(urlTree);
  }

  get products() {
    return this._products;
  }

  get total() {
    return this._total;
  }

  get attributes() {
    return this._attributes;
  }

  isLogedIn() {
    return this.loginService.signedIn;
  }

  get pageIndex() {
    return this._pageIndex;
  }

  get pageSize() {
    return this._pageSize;
  }

  onPageChange(event: PageEvent) {
    this.updateUrl(event.pageIndex, event.pageSize);
  }

  filterProducts(categoryId: number) {
    this._pageIndex = 0;
    this._pageSize = 3;
    if (categoryId == this._categoryId) {
      this._filterData = false;
      this._categoryId = undefined;
      this.getAllProducts(this._pageIndex, this._pageSize);
      this.updateUrl(this._pageIndex, this._pageSize);
      this._attributes = undefined;
    } else {
      this._categoryId = categoryId;
      this._filterData = true;
      this.getFilteredProducts(
        this._pageIndex,
        this._pageSize,
        this._categoryId
      );
      this.updateUrl(this._pageIndex, this._pageSize);
      this.sendRequest(categoryId);
    }
  }

  private sendRequest(id: number) {
    this.categoryService
      .getCategoryAttributes(id)
      .subscribe((attributes: any) => {
        this._attributes = attributes;
      });
  }

  searchProducts() {
    this._pageIndex = 0;
    this._pageSize = 3;
    if (this.searchText != "") {
      this._searchData = true;
      this.getFilteredProductsByName(this.pageIndex, this.pageSize, this.searchText);
      this.updateUrl(this._pageIndex, this._pageSize);
    } else {
      this._searchData = false;
      this.getAllProducts(this._pageIndex, this._pageSize);
      this.updateUrl(this._pageIndex, this._pageSize);
    }
  }

  addNew() {
    this.router.navigate(['/createProduct']);
  }

  getAllProducts(pageIndex: number, pageSize: number) {
    this.productService
      .getCategoryData(pageIndex, pageSize)
      .subscribe((products: any) => {
        this._products = products.content;
        this._total = products.totalElements;
      });
  }

  getFilteredProductsByName(
    pageIndex: number,
    pageSize: number,
    productName: string
  ){
    this.productService
      .filterByProductName(productName, pageIndex, pageSize,)
      .subscribe((products: any) => {
        this._products = products.content;
        this._total = products.totalElements;
      });
  }

  getFilteredProducts(
    pageIndex: number,
    pageSize: number,
    categoryId?: number
  ) {
    this.productService
      .getFilteredData(pageIndex, pageSize, categoryId)
      .subscribe((products: any) => {
        this._products = products.content;
        this._total = products.totalElements;
      });
  }

  filter() {
    const filteredAttributes: any[] = (this._attributes ?? []).filter(attribute => {
      return attribute.userInput !== undefined && attribute.userInput !== '';
    });
    this.productService
      .filterByAttributes(filteredAttributes, this._pageIndex, this._pageSize)
      .subscribe((products: any) => {
        this._products = products.content;
        this._total = products.totalElements;
      });
  }
}
