import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/component/request/auth-request';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  request: AuthRequest = {}

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) { }

  login() {
    this.authService.login(this.request)
      .subscribe(
        data => {
          console.log('Login successful', data);
          this.router.navigate(["/home"]);
        },
        error => {
          console.error('Login failed', error);
        }
      );
  }


}
