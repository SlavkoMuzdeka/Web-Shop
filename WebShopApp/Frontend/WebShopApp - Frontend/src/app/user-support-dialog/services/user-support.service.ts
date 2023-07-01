import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserSupportService {

  constructor(private http: HttpClient) {}

  sendMessage(id: number, content: string, title: string) {
    const message = {
      user: {
        id: id
      },
      title: title,
      content: content
    };
    return this.http.post('http://localhost:8081/messages', message);
  }
}
