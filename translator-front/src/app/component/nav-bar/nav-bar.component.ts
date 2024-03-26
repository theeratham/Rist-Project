import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  guestNavItems: NavBarItem[] = [
    {
      icon: 'bi-house-door',
      title: 'Home',
      routerLink: '/login',
    },
  ];
  userNavItems: NavBarItem[] = [
    {
      icon: 'bi-house-door',
      title: 'Home',
      routerLink: '/home',
    },
    {
      icon: 'bi-music-note-list',
      title: 'My Playlist',
      routerLink: '/playlist',
    },
    {
      icon: 'bi-vinyl-fill',
      title: 'Songs',
      routerLink: '/song',
    },
  ];

  navItems: NavBarItem[] = this.guestNavItems;

  constructor(authService: AuthenticationService) {
    // authService.getTokenPayload().role
    this.navItems = this.userNavItems;
  }

  ngOnInit(): void {}
}

type NavBarItem = {
  icon: string;
  title: string;
  routerLink: string;
};
