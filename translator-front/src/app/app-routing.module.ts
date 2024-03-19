import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './page/login/login.component';
import { RegisterComponent } from './page/register/register.component';
import { HomeComponent } from './page/home/home.component';
import { PlaylistComponent } from './page/playlist/playlist.component';
import { UserProfileComponent } from './page/user-profile/user-profile.component';
import { SongComponent } from './page/song/song.component';

const routes: Routes = [
  { path: '' , redirectTo: '/login', pathMatch: 'full'},
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'playlist',
    component: PlaylistComponent
  },
  {
    path: 'user-profile',
    component: UserProfileComponent
  },
  {
    path: 'song',
    component: SongComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
