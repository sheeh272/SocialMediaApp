import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

    getPostData() {
      return this.http.get<any>('/api/v1/post');
    }

    sendPostData(postData: string) {
      const headers = { 'content-type': 'application/json'}
      return this.http.post<any>('/api/v1/post', postData , {'headers':headers});
    }
}
