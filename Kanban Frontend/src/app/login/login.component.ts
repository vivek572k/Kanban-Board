import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Login } from 'src/assets/model/login';
import { AuthService } from '../service/auth.service';
import { RouterService } from '../service/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService: AuthService, private routeService: RouterService, private form: FormBuilder) { }

  loginForm = this.form.group({
    emailId: ["", [Validators.required, Validators.minLength(2), Validators.pattern("^[a-zA-Z_.+-]+[a-zA-Z0-9_.+-]+@[a-zA-Z-]+\.[a-zA-Z-.]+$")]],
    password: ["", [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]]
  })

  get emailId() { return this.loginForm.get("emailId") }

  get password() { return this.loginForm.get("password") }

  validateUserLogin() {
    
    const login:Login=this.loginForm.value as Login;
    console.log(login);
    
    this.authService.postLogin(login).subscribe((data: string) => {
      console.log(data);
      this.authService.settoken(data);
      console.log(data)
      // localStorage.setItem("token",data)
      // this.authService.login(data);
      console.log(login.emailId);
      // localStorage.setItem("emailId",login.emailId)
      // sessionStorage.getItem("token");
      console.log("validate token "+localStorage.getItem("token"))
      console.log(localStorage);
      this.routeService.navigateToHomeView();
    })
  }
 navigateToSingUp(){
  this.routeService.navigateToSignUpView();
 }
}
