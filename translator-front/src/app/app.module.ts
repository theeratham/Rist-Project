import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './page/home/home.component';
import { LoginComponent } from './page/login/login.component';
import { RegisterComponent } from './page/register/register.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SearchbarComponent } from './component/search-bar/search-bar.component';
import { UserProfileComponent } from './page/user-profile/user-profile.component';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { PlaylistComponent } from './page/playlist/playlist.component';
import { SongPlayerComponent } from './component/song-player/song-player.component';
import { SongComponent } from './page/song/song.component';
import { AuthGuard } from './guard/auth-guard';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    SearchbarComponent,
    UserProfileComponent,
    NavBarComponent,
    PlaylistComponent,
    SongPlayerComponent,
    SongComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
    AppRoutingModule,
  ],
  providers: [HttpClient, AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
