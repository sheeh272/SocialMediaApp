import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient, private cookieService: CookieService) { }

    getPostData() {
      let headers: HttpHeaders = new HttpHeaders();
      let token: String = this.cookieService.get("Jwt");
      headers = headers.append('Authorization',  `Bearer ${token}`);
      return this.http.get<any>('/api/v1/post', {'headers':headers});
    }

    sendPostData(postData: string) {
      let headers: HttpHeaders = new HttpHeaders();
      let token: String = this.cookieService.get("Jwt");
      headers = headers.append('Authorization',  `Bearer ${token}`);
      headers=headers.append('content-type', 'application/json');
      return this.http.post<any>('/api/v1/post', postData , {'headers':headers});
    }
}
