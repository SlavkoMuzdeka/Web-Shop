import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  public postComment(comment: any, product_id?: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.http
        .post(`http://localhost:8081/products/${product_id}/comments`, comment, {
          observe: 'response',
        })
        .subscribe(
          (response: any) => {
            if (response && response.body) {
              resolve(response.body);
            } else {
              reject('Response body is empty');
            }
          }
        );
    });
  }
  
}
