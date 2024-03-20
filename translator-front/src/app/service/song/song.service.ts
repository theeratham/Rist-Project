import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

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

  createSong() {
    return
  }

  deleteSong() {
    return
  }
}
