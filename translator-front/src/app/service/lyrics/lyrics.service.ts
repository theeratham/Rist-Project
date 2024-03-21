import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LyricsRequest } from 'src/app/component/request/lyrics-request';

@Injectable({
  providedIn: 'root'
})
export class LyricsService {
  private url = 'http://localhost:8080/lyrics'

  constructor(private http: HttpClient) { }

  createLyrics(lyricsRequest: LyricsRequest) {
    return this.http.post<any>(`${this.url}/addLyrics`,lyricsRequest)
  }

  deleteLyrics(lyrics_id:number) {
    return this.http.delete<number>(`${this.url}/deleteLyrics/${lyrics_id}`)
  }
}
