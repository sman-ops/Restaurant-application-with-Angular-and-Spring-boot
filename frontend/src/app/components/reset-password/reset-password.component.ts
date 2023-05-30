import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

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
  constructor(private formChildGroup: FormBuilder) {}

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
  }

  resetNewPassword() {
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }
  }
}
