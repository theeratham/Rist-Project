import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRequest } from 'src/app/component/request/user-request';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  request: UserRequest = {}

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private location: Location) { }

  register() {
    this.authService.register(this.request)
      .subscribe(
        data => {
          console.log('Registration successful', data);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Registration failed', error);
        }
      );
  }


  back() {
    this.location.back()
  }

}
