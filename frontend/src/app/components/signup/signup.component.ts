import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from 'src/app/service/security/authentication-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css', '../../../assets/css/login-signup.css'],
})
export class SignupComponent implements OnInit {
  checkoutParentGroup!: FormGroup;

  constructor(
    private formChildGroup: FormBuilder,
    private auth: AuthenticationServiceService,
    private router: Router
  ) {}

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

  signup() {
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }
    this.auth
      .createUser(
        this.checkoutParentGroup.controls['user'].value.email,
        this.checkoutParentGroup.controls['user'].value.password
      )
      .subscribe({
        next: (response) => {
          if (response.result === 1) {
            this.router.navigateByUrl('/login');
          } else {
            alert('email is exist');
          }
        },
      });
  }
}
