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

    getPostsOfUser(id : String){
      let headers: HttpHeaders = new HttpHeaders();
      let token: String = this.cookieService.get("Jwt");
      headers = headers.append('Authorization',  `Bearer ${token}`);
      return this.http.get<any>(`/api/v1/post/?userId=${id}`,{'headers':headers});
    }

    deletePost(id : String){
      let headers: HttpHeaders = new HttpHeaders();
      let token: String = this.cookieService.get("Jwt");
      headers = headers.append('Authorization',  `Bearer ${token}`);
      return this.http.delete<any>(`/api/v1/post/${id}`,{'headers':headers});
    }

    editPost(id : String, postData: string) {
      let headers: HttpHeaders = new HttpHeaders();
      let token: String = this.cookieService.get("Jwt");
      headers = headers.append('Authorization',  `Bearer ${token}`);
      headers=headers.append('content-type', 'application/json');
      return this.http.put<any>(`/api/v1/post/${id}`, postData ,{'headers':headers});
    }

}
