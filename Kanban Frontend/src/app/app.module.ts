import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterService } from './service/register.service';
import { DataServiceService } from './service/data-service.service';
import { AuthInterceptor } from './auth.interceptor';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatRadioModule} from '@angular/material/radio';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTooltipModule} from '@angular/material/tooltip';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AddBoardComponent } from './add-board/add-board.component';
import { NavbarComponent } from './navbar/navbar.component';
import { BoardViewComponent } from './board-view/board-view.component';
import { AddStageComponent } from './add-stage/add-stage.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { BoardListComponent } from './board-list/board-list.component';
import { FooterComponent } from './footer/footer.component';
import { MatNativeDateModule } from '@angular/material/core';
import { TaskViewComponent } from './task-view/task-view.component';
import { AdditionalStageComponent } from './additional-stage/additional-stage.component';
import { MemberComponent } from './member/member.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { LandingPageComponent } from './landing-page/landing-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    SignUpComponent,
    AddBoardComponent,
    NavbarComponent,
    BoardViewComponent,
    AddStageComponent,
    AddTaskComponent,
    BoardListComponent,
    FooterComponent,
    TaskViewComponent,
    AdditionalStageComponent,
    MemberComponent,
    LandingPageComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    MatCardModule,
    MatInputModule,
    MatMenuModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatTableModule,
    MatSidenavModule,
    MatListModule,
    MatBottomSheetModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    DragDropModule,
    MatGridListModule,
    MatTooltipModule,
    MatDividerModule,
    MatRadioModule
  ],
  providers: [RegisterService,DataServiceService,{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
