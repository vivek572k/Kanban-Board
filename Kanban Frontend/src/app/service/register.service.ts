import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUp } from 'src/assets/model/register';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }
  url = "http://localhost:9000/api/v2/save";
  postsignup(form: SignUp): Observable<SignUp> {
    return this.http.post<SignUp>(this.url, form);
  }
}
