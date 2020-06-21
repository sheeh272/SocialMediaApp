import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getUserData() {
      return this.http.get<any>('/api/v1/user');
  }

  registerUser(postData: string){
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    headers=headers.append('content-type', 'application/json');
    return this.http.post<any>('/api/v1/user', postData , {'headers':headers});
  }

  validateUser(username,passcode){
    let url = '/api/v1/user/?username='+username+"&passcode="+passcode;
    console.log(url)
    return this.http.get<any>(url);
  }

  getUserDataFromId(id : String){
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    return this.http.get<any>(`/api/v1/user/${id}`,{'headers':headers});
  }

}
