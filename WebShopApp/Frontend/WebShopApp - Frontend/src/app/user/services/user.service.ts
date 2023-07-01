import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  changeDataAccount(user: any, user_id?: number) {
    return this.http.put(`http://localhost:8081/users/${user_id}`, user);
  }
}
