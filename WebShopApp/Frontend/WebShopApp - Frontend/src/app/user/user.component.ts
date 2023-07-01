import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from '../auth/services/registration.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from '../auth/services/login.service';
import { User } from '../model/user.model';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  private _base64EncodedAvatar?: string;

  private _hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private loginService: LoginService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    const user = this.loginService.activeUser;
    this.form = this.formBuilder.group({
      firstName: [user?.firstName, Validators.required],
      lastName: [user?.lastName, Validators.required],
      city: [user?.city, Validators.required],
      password: [user?.password, Validators.required],
      mail: [user?.mail, [Validators.required, Validators.email]],
      avatar: [null],
    });
  }

  getErrorMessage(controlName: string): string {
    const control = this.form.get(controlName);
    if (control?.hasError('required')) {
      return 'You must enter a value';
    }
    return control?.hasError('email') ? 'Not a valid email' : '';
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (event: any) => {
      this._base64EncodedAvatar = event.target.result.split(',')[1];
    };

    reader.readAsDataURL(file);
  }

  changeData() {
    const formData = this.form.value;
    const user = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      city: formData.city,
      password: formData.password,
      mail: formData.mail,
      avatar: this._base64EncodedAvatar? this._base64EncodedAvatar : this.loginService.activeUser?.avatar,
    };

    this.userService
      .changeDataAccount(user, this.loginService.activeUser?.id)
      .subscribe(
        (response: any) => {
          this.snackBar.open('Your account data has been changed!', undefined, {
            duration: 2000,
          });
          if(this.loginService.activeUser){
            this.loginService.activeUser = response.user;
          }
          this.router.navigate(['/']);
        },
        () => {
          this.snackBar.open(
            'Account data changing failed. Please try again.',
            undefined,
            {
              duration: 2000,
            }
          );
        }
      );
  }

  togglePasswordVisibility() {
    this._hidePassword = !this._hidePassword;
  }

  get hidePassword() {
    return this._hidePassword;
  }
}
