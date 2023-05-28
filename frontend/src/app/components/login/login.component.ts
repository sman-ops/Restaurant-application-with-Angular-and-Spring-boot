import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from 'src/app/service/security/authentication-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../../../assets/css/login-signup.css'],
})
export class LoginComponent implements OnInit {
  checkoutParentGroup!: FormGroup;

  constructor(
    private formChildGroup: FormBuilder,
    private auth: AuthenticationServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.myFormLogin();
  }

  myFormLogin() {
    this.checkoutParentGroup = this.formChildGroup.group({
      user: this.formChildGroup.group({
        email: [''],
        password: [''],
      }),
    });
  }

  login() {
    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
      return;
    }

    this.auth
      .userActive(
        this.checkoutParentGroup.controls['user'].value.email,
        this.checkoutParentGroup.controls['user'].value.password
      )
      .subscribe({
        next: (response) => {
          let ac = response.active;
          if (ac === 1) {
            this.auth
              .executeAuthentication(
                this.checkoutParentGroup.controls['user'].value.email,
                this.checkoutParentGroup.controls['user'].value.password
              )
              .subscribe({
                next: (response) => {
                  // console.log(response);
                  this.router.navigateByUrl('/orders');
                },
              });
          } else if (ac === 0) {
            this.router.navigateByUrl('/active');
          } else {
            alert('invalid email or pass');
          }
        },
      });
    /*
    this.auth
      .executeAuthentication(
        this.checkoutParentGroup.controls['user'].value.email,
        this.checkoutParentGroup.controls['user'].value.password
      )
      .subscribe({
        next: (response) => {
          // console.log(response);
          this.router.navigateByUrl('/orders');
        },
        error: (err) => {
          console.log(err);
        },
      });

      */
  }
}
