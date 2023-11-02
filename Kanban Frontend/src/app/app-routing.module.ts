import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { AddBoardComponent } from './add-board/add-board.component';
import { authGuard } from './auth.guard';
import { BoardViewComponent } from './board-view/board-view.component';
import { AddStageComponent } from './add-stage/add-stage.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { TaskViewComponent } from './task-view/task-view.component';
import { AdditionalStageComponent } from './additional-stage/additional-stage.component';
import { MemberComponent } from './member/member.component';
import { canDeactivateGuard } from './can-deactivate.guard';
import { LandingPageComponent } from './landing-page/landing-page.component';


const routes: Routes = [
  {path:"",redirectTo:"/landingview",pathMatch:"full"},
  {path:"landingview",component:LandingPageComponent},
  {path:"login",component:LoginComponent},
  {path:"signUp",component:SignUpComponent},
  {path:"home",component:HomeComponent,canActivate:[authGuard]},
  {path:"addboard",component:AddBoardComponent,canActivate:[authGuard],canDeactivate:[canDeactivateGuard]},
  {path:"addstage",component:AddStageComponent,canActivate:[authGuard],canDeactivate:[canDeactivateGuard]},
  {path:"addtask/:id/:id1",component:AddTaskComponent,canActivate:[authGuard],canDeactivate:[canDeactivateGuard]},
  {path:"boardview/:id",component:BoardViewComponent,canActivate:[authGuard]},
  {path:"taskview/:id/:id1/:id2",component:TaskViewComponent,canActivate:[authGuard]},
  {path:"additionalstage/:id",component:AdditionalStageComponent,canActivate:[authGuard]},
  {path:"member",component:MemberComponent,canActivate:[authGuard],canDeactivate:[canDeactivateGuard]},
 
  // canActivate:[authGuard]
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
