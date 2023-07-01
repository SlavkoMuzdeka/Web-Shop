import { LoginService } from './login.service';
import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,
} from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class GuardService implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(): boolean {
    if (this.loginService.signedIn || this.loginService.activateAccount) {
      return true;
    } else {
      this.router.navigate(['']);
      return false;
    }
  }
}
