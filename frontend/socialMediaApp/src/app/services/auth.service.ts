import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
//import { User} from "../models/GlobalInterfaces"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getToken(username:string, passcode: string) {
      const headers = { 'content-type': 'application/json'}
      let params = new HttpParams().set("username", username).set("passcode",passcode);
      return this.http.get<any>('/api/v1/auth', {'headers':headers, 'params': params });
  }

  validateToken(token: String) {
      const headers = { 'content-type': 'application/json'}
      return this.http.post<any>('/api/v1/auth', token , {'headers':headers});
  }

  getUserId() {
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    headers=headers.append('content-type', 'application/json');
    return this.http.get<any>('/api/v1/auth', {'headers':headers});
  }

}
