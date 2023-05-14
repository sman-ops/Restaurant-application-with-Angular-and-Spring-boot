import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  checkoutParentGroup!: FormGroup;

  constructor(private formChildGroup: FormBuilder) {}

  ngOnInit(): void {
    this.myFormSignup();
  }

  myFormSignup() {
    this.checkoutParentGroup = this.formChildGroup.group({
      user: this.formChildGroup.group({
        email: [''],
        password: [''],
      }),
    });
  }

  signup() {}
}
