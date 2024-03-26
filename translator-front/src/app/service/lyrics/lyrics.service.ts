import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LyricsService {
  private url = 'http://localhost:8080/lyrics';

  constructor(private http: HttpClient) {}

  createLyrics(file:FormData) {
    return this.http.post<any>(`${this.url}/addLyrics`,file );
  }
}
