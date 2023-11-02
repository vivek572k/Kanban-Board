import { Component } from '@angular/core';
import { RegisterService } from '../service/register.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SignUp } from 'src/assets/model/register';
import { RouterService } from '../service/router.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  constructor(private form: FormBuilder,private registerService: RegisterService, private snackBar: MatSnackBar,private routerservice:RouterService) {
  }

  // onNoClick(): void {
  //   this.dialogRef.close();
  // }
  signupform = this.form.group({
    emailId: ["", [Validators.required, Validators.minLength(2), Validators.email]],
    password: ["", [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    userName: ["", [Validators.required, Validators.minLength(2), Validators.pattern("^[a-zA-Z \-\']+")]],
  
  })
  get emailId() { return this.signupform.get("emailId") }

  get password() { return this.signupform.get("password") }

  get userName() { return this.signupform.get("userName") }

 






  onsubmit() {
    const form: SignUp = this.signupform.value as SignUp;
    console.log(form);
    this.registerService.postsignup(form).subscribe({
      next:data => {
      this.snackBar.open('Registered Successfully', 'Success', {
        duration: 5000
      })
      this.routerservice.navigateToLoginView();
    },
    error:error=>{
      alert("User already registered");
    }
    })
    
  }
  


}
