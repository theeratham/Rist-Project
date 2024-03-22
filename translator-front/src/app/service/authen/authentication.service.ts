import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../../component/request/auth-request';
import { UserRequest } from '../../component/request/user-request';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private url = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(authRequest: AuthRequest) {
    return this.http.post<any>(`${this.url}/login`, authRequest);
  }

  register(userRequest: UserRequest) {
    return this.http.post<any>(`${this.url}/register`, userRequest);
  }

  isTokenValid() {
    try {
      return !!jwtDecode(localStorage.getItem('token') ?? '');
    } catch (error) {
      return false;
    }
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getTokenPayload() {
    if (!this.isTokenValid()) {
      throw new Error("Token doesn't exist or is not valid.");
    }

    return jwtDecode(localStorage.getItem('token')!);
  }
}
