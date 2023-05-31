import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from 'src/app/service/security/authentication-service.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: [
    './reset-password.component.css',
    '../../../assets/css/login-signup.css',
  ],
})
export class ResetPasswordComponent implements OnInit {
  checkoutParentGroup!: FormGroup;
  checkoutParentGroupReset!: FormGroup;
  enableForm: boolean = true;
  constructor(
    private formChildGroup: FormBuilder,
    private auth: AuthenticationServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  myFormLogin() {
    this.checkoutParentGroup = this.formChildGroup.group({
      user: this.formChildGroup.group({
        email: new FormControl('', [Validators.required]),
      }),
    });
  }

  myFormLoginReset() {
    this.checkoutParentGroupReset = this.formChildGroup.group({
      newUser: this.formChildGroup.group({
        code: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required]),
      }),
    });
  }

  get code() {
    return this.checkoutParentGroupReset.get('newUser.code');
  }

  get password() {
    return this.checkoutParentGroupReset.get('newUser.password');
  }

  get email() {
    return this.checkoutParentGroup.get('user.email');
  }

  done() {
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }
    this.auth
      .checkEmail(this.checkoutParentGroup.controls['user'].value.email)
      .subscribe({
        next: (response) => {
          if (response.result === 1) {
            this.enableForm = false;
          } else {
            alert('email does not exist');
          }
        },
      });
  }

  resetNewPassword() {
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }
    this.auth
      .resetPassword(
        this.checkoutParentGroup.controls['user'].value.email,
        this.checkoutParentGroupReset.controls['newUser'].value.code,
        this.checkoutParentGroupReset.controls['newUser'].value.password
      )
      .subscribe({
        next: (response) => {
          if (response.result === 1) {
            alert('success edit password');
            this.router.navigateByUrl('/login');
          } else {
            alert('invalid code');
          }
        },
      });
  }
}
