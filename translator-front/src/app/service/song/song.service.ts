import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SongRequest } from 'src/app/component/request/song-request';

@Injectable({
  providedIn: 'root'
})
export class SongService {
  private url = 'http://localhost:8080/song'

  constructor(private http: HttpClient) { }

  getSong() {
    return this.http.get<any>(`${this.url}/findByName`)
  }

  getAllSong() {
    return this.http.get<any[]>(`${this.url}/findAll`)
  }

  createSong(songRequest: SongRequest) {
    return this.http.post<any>(`${this.url}/addSong`,songRequest)
  }

  deleteSong(song_id:number) {
    return this.http.delete<any>(`${this.url}/deleteSong/${song_id}`)
  }

  search(input:string) {
    return this.http.post<any>(`${this.url}/search`,input)
  }
}
