import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { Stage } from 'src/assets/model/stage';

@Component({
  selector: 'app-additional-stage',
  templateUrl: './additional-stage.component.html',
  styleUrls: ['./additional-stage.component.css']
})
export class AdditionalStageComponent {

  stages:Stage[]=[]
  constructor(private dataservice: DataServiceService, private routerservice: RouterService, private form: FormBuilder, private activateroute: ActivatedRoute) { }
  stageForm = this.form.group({
    stageName: ["", Validators.required]

  })
  get stageName() {
    return this.stageForm.get("stageName")
  }

  addStage(){
    const stage:Stage=this.stageForm.value as Stage
    this.activateroute.paramMap.subscribe((params:any)=>{
      let boardName=params.get('id')??""
      this.dataservice.saveBoardStageToSet(stage,boardName).subscribe({
        next:data=>{
            this.stages=data
            alert("stage added successfully")
            this.routerservice.navigateToBoardView(boardName)
        }
      })

    })
  }
}
