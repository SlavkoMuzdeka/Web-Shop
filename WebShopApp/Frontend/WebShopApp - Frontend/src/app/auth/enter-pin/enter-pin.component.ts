import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-enter-pin',
  templateUrl: './enter-pin.component.html',
  styleUrls: ['./enter-pin.component.css'],
})
export class EnterPinComponent implements OnInit{
  public form: FormGroup = new FormGroup({});
  private _pin: string = '';

  constructor(
    private registerService: RegistrationService,
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      pin: [null, Validators.pattern(/^[0-9]{4}$/)],
    });
  }

  sendPin(form: any) {
    this._pin = form.get('pin').value;
    const data = {
      pin: this._pin,
      value: this.registerService.id? this.registerService.id: this.loginService.username,
    };
    this.registerService.activate(data).subscribe((response: any) => {
      if (response.status_code == 200) {
        let user;
        if(this.registerService.username && this.registerService.password){
          user = {
            userName: this.registerService.username,
            password: this.registerService.password,
          };
        }else{
          user = {
            userName: this.loginService.username,
            password: this.loginService.password,
          };
        }
        this.loginService.login(user).subscribe((response: any) => {
          if (response.status_code == 200) {
            this.loginService.activeUser = response.user;
            this.loginService.signedIn = true;
            this.router.navigate(['/']);
          }
        });
      } else if (response.status_code == 400) {
        this.snackBar.open('Invalid PIN', undefined, {
          duration: 2000,
        });
        this.router.navigate(['/login']);
      }
    });
  }

  getErrorMessage() {

  }

  get pin() {
    return this._pin;
  }

  set pin(pin: string) {
    this._pin = pin;
  }
}
