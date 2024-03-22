import { Component, OnInit } from '@angular/core';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  user: JwtPayload;

  constructor(private authService: AuthenticationService) {
    this.user = authService.getTokenPayload();
  }

  ngOnInit(): void {}
}
