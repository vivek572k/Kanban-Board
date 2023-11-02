import { Component, Input, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { ActivatedRoute } from '@angular/router';
import { RouterService } from '../service/router.service';
import { Board } from 'src/assets/model/board';
import { Stage } from 'src/assets/model/stage';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Task } from 'src/assets/model/task';

@Component({
  selector: 'app-board-view',
  templateUrl: './board-view.component.html',
  styleUrls: ['./board-view.component.css']
})
export class BoardViewComponent implements OnInit {
  boards: Board = {
    boardName: '',
    stages: []
  }
  stages:Stage={
    stageName: ''
  }



  constructor(private dataservice: DataServiceService, private activateroute: ActivatedRoute, private routerservice: RouterService) { }
  ngOnInit(): void {
    this.activateroute.paramMap.subscribe((params:any) => {
      let boardName = params.get('id') ?? ""
      console.log("boarData"+boardName);
      this.dataservice.getBoardByName(boardName).subscribe((boardData)=>{
        this.boards=boardData
        console.log(this.boards);
        
      })
    })

    // this.activateroute.paramMap.subscribe((params:any)=>{
    //   let boardName=params.get('id')??""
    //   console.log("ngOnchanges"+boardName);
      
    //   this.dataservice.getAllStagesByBoardName(boardName).subscribe({
    //     next:data=>{
    //       this.stages=data;
    //       console.log("ngonchanges stage"+this.stages);
          
    //     }
    //   })
    // })

  }
  

  deleteStages(stageName:string) {
    this.activateroute.paramMap.subscribe((params) => {

      let boardName=params.get('id')??""

      this.dataservice.deleteStage(boardName,stageName).subscribe((stage) => {
        this.stages = stage;
        alert("Stage deleted successfully")
        
      })
    })
  }
  onTaskDropped(event:CdkDragDrop<Task[]|undefined>):void{
      // const taskToMove = event.item.data
      if (event.previousContainer === event.container) {
        moveItemInArray(event.container.data!, event.previousIndex, event.currentIndex);
      } else {
        const taskToMove = event.item.data
        transferArrayItem(
          event.previousContainer.data!,
          event.container.data!,
          event.previousIndex,
          event.currentIndex,
        );
      }
      // this.activateroute.paramMap.subscribe((params)=>{
      //   let boardName = params.get('id')??""

      //   this.dataservice.updateBoard(boardName,this.boards).subscribe({
      //     next:data=>{
      //       console.log(this.boards);
            
      //       this.boards=data;
      //     }
      //   })
      // })
     
    }
    // ngOnChanges(){
    //   this.activateroute.paramMap.subscribe((params:any)=>{
    //     let boardName=params.get('id')??""
    //     console.log("ngOnchanges"+boardName);
        
    //     this.dataservice.getAllStagesByBoardName(boardName).subscribe({
    //       next:data=>{
    //         this.stages=data;
    //         console.log("ngonchanges stage"+this.stages);
            
    //       }
    //     })
    //   })
      
    // }
      //  Determine the new stage for the task (your logic may vary)
  // newStage : Stage ={
  //   stageName:
  // };// or calculate the new stage based on your data

  // Update the stage of the task
  // taskToMove.stages = newStage;

  // You might want to save this change to your data source here

  // Notify Angular CDK that the drop is complete
  
  }

