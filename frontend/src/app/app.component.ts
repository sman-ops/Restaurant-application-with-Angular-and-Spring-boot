import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationServiceService } from './service/security/authentication-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'frontend';

  constructor(
    private cook: CookieService,
    private auth: AuthenticationServiceService
  ) {}

  ngOnInit(): void {
    if (this.isCookie()) {
      localStorage.setItem('email', this.cook.get('email'));
      localStorage.setItem('token', this.cook.get('token'));
    }
  }

  isCookie() {
    if (this.cook.get('email') === '' || this.cook.get('token') === '') {
      return false;
    }
    return true;
  }
}
