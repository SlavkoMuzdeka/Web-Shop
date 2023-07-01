import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private _signedIn: boolean = false;
  private _activeUser: User | null = null;
  private _activateAccount: boolean = false;
  private _username?: string;
  private _password?: string;

  constructor(private router: Router, private http: HttpClient) {}

  public login(user: any) {
    return this.http.post(`http://localhost:8081/users/login`, user);
  }

  public isLogedIn(user: any): boolean {
    this.login(user);
    if (this._signedIn) {
      return true;
    }
    return false;
  }

  public getImage() {
    return this._activeUser?.avatar;
  }

  get id(){
    return this._activeUser?.id;
  }

  get activeUser() {
    return this._activeUser;
  }

  set activeUser(newUser: User | null) {
    this._activeUser = newUser;
  }

  public logout() {
    this._activeUser = null;
    this._signedIn = false;
    this.router.navigate(['/']);
  }

  get signedIn() {
    return this._signedIn;
  }

  set signedIn(signedId: boolean){
    this._signedIn = signedId;
  }

  get activateAccount() {
    return this._activateAccount;
  }

  set activateAccount(activateAccount: boolean) {
    this._activateAccount = activateAccount;
  }

  get username() {
    return this._username;
  }

  set username(username: string | undefined){
    this._username = username;
  }

  get password() {
    return this._password;
  }

  set password(password: string | undefined){
    this._password = password;
  }
}
