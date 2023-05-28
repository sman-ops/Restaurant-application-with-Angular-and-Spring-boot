import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-code-activation',
  templateUrl: './code-activation.component.html',
  styleUrls: [
    './code-activation.component.css',
    '../../../assets/css/login-signup.css',
  ],
})
export class CodeActivationComponent implements OnInit {
  email: string = '';
  checkoutParentGroup!: FormGroup;
  constructor(private formChildGroup: FormBuilder) {}

  ngOnInit(): void {
    this.email = localStorage.getItem('emailActive') || '';
    this.myFormLogin();
  }

  myFormLogin() {
    this.checkoutParentGroup = this.formChildGroup.group({
      user: this.formChildGroup.group({
        code: new FormControl('', [Validators.required]),
      }),
    });
  }
  get code() {
    return this.checkoutParentGroup.get('user.code');
  }

  done() {
    console.log(this.checkoutParentGroup.controls['user'].value.code);
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }
  }
}
