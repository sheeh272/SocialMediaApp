import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthService} from "../services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient, private cookieService: CookieService,
  private authService: AuthService) { }

  public uploadImage(image: File) {
    const formData = new FormData();
    formData.append('image', image);
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    return this.http.post('/api/v1/image', formData,{'headers':headers});
  }

  public getProfilePicture(){
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    return this.http.get('/api/v1/image', {'headers':headers, 'responseType': "blob"});
  }
  public getProfilePictureByName(name){
    let headers: HttpHeaders = new HttpHeaders();
    let token: String = this.cookieService.get("Jwt");
    headers = headers.append('Authorization',  `Bearer ${token}`);
    return this.http.get(`/api/v1/image/?name=${name}`, {'headers':headers, 'responseType': "blob"});
  }
}
