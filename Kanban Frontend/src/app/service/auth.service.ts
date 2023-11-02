import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/assets/model/login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }
  login_url="http://localhost:9000/api/v1/login"
  postLogin(login:Login):Observable<string> {
    return this.http.post(this.login_url, login,{responseType:"text"});
  }

  loggedStatus: boolean=false; 
  
  isLoggedIn:String | undefined 
  

  // login(email: string|undefined) {
  //   this.isLoggedIn=email;
  //   this.loggedStatus=true;
    
  // }

  // logout(){
  //   this.isLoggedIn=""
  //   this.loggedStatus=false;
 
  // }
  status:boolean = true;
  settoken(to:string){
    localStorage.setItem("token",to);
    localStorage.setItem("status", JSON.stringify(status));
  }

  gettoken(){
    localStorage.getItem("token");
  }

  cleartoken(){
    localStorage.removeItem("token");
    localStorage.removeItem("status");
  }

  getStatus(){
    return localStorage.getItem("status")
  }

}
