import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PlaylistRequest } from 'src/app/component/request/playlist-request';

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

  createPlaylist(PlaylistRequest: PlaylistRequest) {
    return this.http.post<any>(`${this.url}/addPlaylist`,PlaylistRequest)
  }

  editPlaylist(name:string) {
    return this.http.put<string>(`${this.url}/editPlaylist`,name)
  }

  deletePlaylist(playlist_id:number) {
    return this.http.delete<number>(`${this.url}/deletePlaylist/${playlist_id}`)
  }

  addSongToPlaylist(song_id:number,playlist_id:number) {
    return this.http.post<any>(`${this.url}/addPlaylist`,{song_id,playlist_id})
  }
}
