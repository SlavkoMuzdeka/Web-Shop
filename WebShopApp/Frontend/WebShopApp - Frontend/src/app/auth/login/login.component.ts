import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  private _hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  public login(form: any) {
    const user = {
      userName: form.get('username').value,
      password: form.get('password').value,
    };
    this.loginService.username = form.get('username').value;
    this.loginService.password = form.get('password').value;
    this.loginService.login(user).subscribe((response: any) => {
      if (response.status_code == 200) {
        this.loginService.activeUser = response.user;
        this.loginService.signedIn = true;
        this.router.navigate(['/']);
      } else if (response.status_code == 401) {
        this.loginService.signedIn = false;
        this.snackBar.open('Invalid credentials', undefined, {
          duration: 2000,
        });
      } else if (response.status_code == 400) {
        this.loginService.activateAccount = true;
        this.router.navigate(['/enter-pin']);
      }
    });
  }

  getErrorMessage(controlName: string): string {
    const control = this.form.get(controlName);
    if (control?.hasError('required')) {
      return 'You must enter a value';
    }
    return '';
  }

  togglePasswordVisibility() {
    this._hidePassword = !this._hidePassword;
  }

  get hidePassword() {
    return this._hidePassword;
  }

}
