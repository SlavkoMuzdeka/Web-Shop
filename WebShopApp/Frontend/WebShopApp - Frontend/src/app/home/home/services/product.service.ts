import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Attribute } from 'src/app/model/attribute.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  private productsUrl: string = 'http://localhost:8081/products';

  getCategoryData(pageIndex: number, pageSize: number) {
    return this.http.get(
      `${this.productsUrl}?page=${pageIndex}&size=${pageSize}`
    );
  }

  getFilteredData(pageIndex: number, pageSize: number, id?: number) {
    return this.http.get(
      `${this.productsUrl}/filter/${id}?page=${pageIndex}&size=${pageSize}`
    );
  }

  getAllPurchasedProducts(userId?: number) {
    return this.http.get(
      `${this.productsUrl}/purchasedProducts/${userId}`
    );
  }

  getAllActiveProducts(userId?: number) {
    return this.http.get(
      `${this.productsUrl}/activeProducts/${userId}`
    );
  }

  getAllFinishedProducts(userId?: number) {
    return this.http.get(
      `${this.productsUrl}/finishedProducts/${userId}`
    );
  }

  getProduct(id: number) {
    return this.http.get(`${this.productsUrl}/` + id);
  }

  buyProduct(id: any, product_id: number): Promise<boolean> {
    const user = {
      id: id,
    };
    return new Promise<boolean>((resolve, reject) => {
      this.http
        .post(`${this.productsUrl}/${product_id}/buy`, user, {
          observe: 'response',
        })
        .subscribe(
          (response: any) => {
            if (response && response.body) {
              resolve(true);
            } else {
              reject(false);
            }
          }
        );
    });
  }

  deleteProduct(id: number) {
    return this.http.delete(`${this.productsUrl}/` + id);
  }

  finishProduct(id: number) {
    return this.http.put(`${this.productsUrl}/` + id, null);
  }

  createProduct(product: any) {
    return this.http.post(`${this.productsUrl}`, product);
  }

  filterByProductName(productName: string, pageIndex: number, pageSize: number){
    return this.http.get(`${this.productsUrl}/${productName}/filteredByName?page=${pageIndex}&size=${pageSize}`);
  }

  filterByAttributes(attributes: any, pageIndex: number, pageSize: number) {
    return this.http.post(`${this.productsUrl}/filterByAttributes?page=${pageIndex}&size=${pageSize}`, attributes);
  }
}
