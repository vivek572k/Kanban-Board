import { Component } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Board } from 'src/assets/model/board';
import { CanComponentDeactivate } from '../deactivate.guard';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-board',
  templateUrl: './add-board.component.html',
  styleUrls: ['./add-board.component.css']
})
export class AddBoardComponent implements CanComponentDeactivate{
  service: any;
  router: any;

  constructor(private dataservice:DataServiceService,private routerservice:RouterService,private form:FormBuilder){}
  emailId= localStorage.getItem("emailId")||'{}';
  submitStatus:boolean=false

  boardForm = this.form.group({
    boardName: ["", Validators.required],

  })

  get boardName() { return this.boardForm.get("boardName") }
  get stageSet() { return this.boardForm.get("stageSet") }


  saveBoard() {
    console.log("save board"+this.emailId);
    const board: Board = this.boardForm.value as Board
    this.dataservice.saveUserBoardToList(board).subscribe(() => {
      localStorage.setItem("boardName",board.boardName)
      console.log("boardName while saving board"+localStorage.getItem("boardName"));
      this.submitStatus=true;
      console.log("working" + board)
      this.routerservice.navigateToAddStageView();
     
     
      console.log("working")
    });
  }

  canDeactivate():any{
    if (!this.submitStatus) {
      this.submitStatus = confirm("Are you sure to leave the page,the details you have entered is not stored. Click 'OK' to exit (or) 'Cancel' to continue");
      return this.submitStatus;
    }
    return true
  }
    
  

}


