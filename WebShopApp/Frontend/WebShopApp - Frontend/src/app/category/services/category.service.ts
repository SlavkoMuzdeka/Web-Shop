import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  
  constructor(private http: HttpClient) { }

  public getCategoryData(){
    return this.http.get('http://localhost:8081/categories');
  }

  public getCategoryAttributes(id: number) {
    return this.http.get(`http://localhost:8081/categories/${id}/attributes`);
  }
}
