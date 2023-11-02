import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router:Router) { }
  navigateToHomeView() {
    this.router.navigate(["home"]);
  }
  navigateToSignUpView(){
    this.router.navigate(["signUp"])
  }
  navigateToLoginView() {
    this.router.navigate(["login"]);
}
navigateToaddboardView(){
  this.router.navigate(["addboard"])
}
navigateToaddtaskView(){
  this.router.navigate(["addtask"])
}
navigateToBoardView(boardName:string){
  this.router.navigate(["boardview/"+boardName])
}
navigateToAddStageView(){
  this.router.navigate(["addstage"])
}
navigateToMemberView(){
  this.router.navigate(["member"])
}
}
