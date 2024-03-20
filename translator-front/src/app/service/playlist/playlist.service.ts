import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  private url = 'http://localhost:8080/playlist'

  constructor(private http: HttpClient) { }

  getPlaylist() {
    return this.http.get<any>(`${this.url}/findByName`)
  }

  getAllPlaylist() {
    return this.http.get<any[]>(`${this.url}/findAll`)
  }

  createPlaylist() {
    return
  }

  editPlaylist() {
    return
  }

  deletePlaylist() {
    return
  }

  addSongToPlaylist() {
    return
  }
}
