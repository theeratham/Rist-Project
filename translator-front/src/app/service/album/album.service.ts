import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AlbumRequest } from 'src/app/component/request/album-request';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  private url = 'http://localhost:8080/album'

  constructor(private http:HttpClient) { }

  getAlbum() {
    return this.http.get<any>(`${this.url}/findByName`)
  }

  createAlbum(
    albumRequest: AlbumRequest
  ) {
    return this.http.post<any>(`${this.url}/addAlbum`,albumRequest)
  }
}
