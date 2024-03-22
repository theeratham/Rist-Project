import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { AuthRequest } from 'src/app/component/request/auth-request';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  request: AuthRequest = {};
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  loginFailed: boolean = false;

  login() {
    this.authService.login(this.request).subscribe(
      (data) => {
        this.authService.setToken(data.message as string);
        this.router.navigate(['/home']);
      },
      (error) => {
        console.error('Login failed', error);
        this.loginFailed = true;
      }
    );
  }
}
