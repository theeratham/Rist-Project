import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AudioService {
  private url = 'http://localhost:8080/user/song/getSongId'

  constructor(private http: HttpClient) { }

  getSong(): Observable<string> {
    return this.http.get<string>(this.url)
  }
}
