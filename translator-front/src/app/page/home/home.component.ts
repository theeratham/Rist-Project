import { Component, OnInit } from '@angular/core';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import { AuthenticationService } from 'src/app/service/authen/authentication.service';
import { SongService } from 'src/app/service/song/song.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  user: JwtPayload
  searchResult:any[] | undefined

  constructor(
    private authService: AuthenticationService,
    private songService: SongService
    ) {
    this.user = authService.getTokenPayload()
  }

  ngOnInit(): void {}

  onSearchInput(input: string) {
    this.songService.search(input).subscribe((data) => {
      this.searchResult = data;
    });
  }

}
