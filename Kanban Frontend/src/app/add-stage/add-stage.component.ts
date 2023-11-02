import { Component } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Stage } from 'src/assets/model/stage';
import { CanComponentDeactivate } from '../deactivate.guard';

@Component({
  selector: 'app-add-stage',
  templateUrl: './add-stage.component.html',
  styleUrls: ['./add-stage.component.css']
})
export class AddStageComponent implements CanComponentDeactivate {

  stages: Stage[] = []
  submitStatus:boolean=false

  constructor(private dataservice: DataServiceService, private routerservice: RouterService, private form: FormBuilder, private activateroute: ActivatedRoute) { }
  stageForm = this.form.group({
    stageName: ["", Validators.required]

  })
  get stageName() {
    return this.stageForm.get("stageName")
  }

  saveStage() {

    const stage: Stage = this.stageForm.value as Stage
    // const boardName:any=this.selectedBoard?.boardName
    // console.log("save stage"+boardName);
     const boardName:any=localStorage.getItem("boardName")
    // this.activateroute.paramMap.subscribe((params) => {
    //   let boardname = params.get('id') ?? ""
      console.log("savestage"+boardName);
      
      this.dataservice.saveBoardStageToSet(stage, boardName).subscribe({
        next: result => {
          this.stages = result;
          this.submitStatus=true;
          this.routerservice.navigateToBoardView(boardName);
        }
      })
    // })
  }
  addStage() {
   const stage:Stage=this.stageForm.value as Stage
   const boardName:any=localStorage.getItem("boardName")
  //  this.activateroute.paramMap.subscribe((params)=>{
  //   let boardname = params.get('id')??""
    this.dataservice.saveBoardStageToSet(stage,boardName).subscribe({
      next:result=>{
        this.stages= result;
        alert("Stage added successfully")
        this.stageForm.reset();
        this.submitStatus=true;
        this.routerservice.navigateToAddStageView()
      }
    })
  //  })

  }

  canDeactivate():any{
    if (!this.submitStatus) {
      this.submitStatus = confirm("Are you sure to leave the page,the details you have entered is not stored. Click 'OK' to exit (or) 'Cancel' to continue");
      return this.submitStatus;
    }
    return true
  }
}