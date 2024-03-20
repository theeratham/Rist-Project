import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArtistRequest } from 'src/app/component/request/artist-request';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private url = 'http://localhost:8080/artist'

  constructor(private http: HttpClient) { }

  getArtist() {
    return this.http.get<any>(`${this.url}/findByName`)
  }

  createArtist(
    artistRequest: ArtistRequest
  ) {
    return this.http.post<any>(`${this.url}/addArtist`,artistRequest)
  }
}
