import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private _id?: number;
  private _username?: string;
  private _password?: string;

  constructor(private http: HttpClient) { }

  register(user: any){
    return this.http.post('http://localhost:8081/users', user);
  }

  activate(user: any) {
    return this.http.put(`http://localhost:8081/users/activate`, user);
  }

  get id() {
    return this._id;
  }

  set id(id: number | undefined){
    this._id = id;
  }

  get username() {
    return this._username;
  }

  set username(username: string | undefined) {
    this._username = username;
  }

  get password() {
    return this._password;
  }
  
  set password(password: string | undefined) {
    this._password = password;
  }
}
