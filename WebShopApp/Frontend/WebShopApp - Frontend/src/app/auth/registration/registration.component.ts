import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registration.service';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  private _base64EncodedAvatar?: string;

  constructor(
    private formBuilder: FormBuilder,
    private registerService: RegistrationService,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      city: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      mail: [null, [Validators.required, Validators.email]],
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

  register() {
    const formData = this.form.value;
    const user = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      city: formData.city,
      userName: formData.username,
      password: formData.password,
      mail: formData.mail,
      avatar: this._base64EncodedAvatar? this._base64EncodedAvatar: null
    };
    this.registerService.register(user).subscribe(
      (response: any) => {
        if(response.status_code == 201) {
          this.registerService.id = response.user_id;
          this.registerService.username = this.form.value.username;
          this.registerService.password = this.form.value.password;
        }
      },
    );
    this.loginService.activateAccount = true;
    this.router.navigate(['/enter-pin']);
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (event: any) => {
      this._base64EncodedAvatar = event.target.result.split(',')[1];
    };

    reader.readAsDataURL(file);
  }
}
