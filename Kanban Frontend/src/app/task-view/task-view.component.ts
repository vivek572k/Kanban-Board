import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { ActivatedRoute } from '@angular/router';
import { Task } from 'src/assets/model/task';
import { TeamMember } from 'src/assets/model/teamMember';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit{
  teamMember:TeamMember[]=[]
  taskList:Task[]=[]
  
 taskDetails:Task={
   taskTitle: ''
 }
  stageNames: any;
// tasks:Task={
//   taskTitle: ''
// }


  constructor(private dataservice:DataServiceService,private routerservice:RouterService,private activateroute:ActivatedRoute){}
  ngOnInit(): void {
   
    this.dataservice.getAllMembersByEmailId().subscribe({
      next:data=>{
        this.teamMember=data;
      },
      error:error=>{
        alert("something went wrong while fetching Member list")
      }
      
    })

    this.activateroute.paramMap.subscribe((params:any)=>{
      let boardName = params.get('id')??""
      console.log("updatetask"+boardName);
      let stageName = params.get('id1')??""
      console.log("updatetask"+stageName);
      let tasktitle = params.get('id2')??""
      console.log("updatestask"+tasktitle);
   
    this.dataservice.getTaskByTaskName(boardName,stageName,tasktitle).subscribe({
      next:data=>{
        this.taskDetails=data;
        console.log("task obj"+this.taskDetails);
        
      }
    })
  })

  this.activateroute.paramMap.subscribe((params:any)=>{
    let boardName = params.get('id')??""
  
 this.dataservice.getAllStagesByBoardName(boardName).subscribe({
      next: data => {
        this.stageNames = data;
      },
      error: error => {
        alert('something went wrong while fetching stages')
      }
    })
  })
 
  }
  
  date: Date = new Date();


  updateTask(){
    console.log("inside update task method");
    this.activateroute.paramMap.subscribe((params:any)=>{
      let boardName = params.get('id')??""
      console.log("updatetask"+boardName);
      let stageName = params.get('id1')??""
      console.log("updatetask"+stageName);
      let tasktitle = params.get('id2')??""
      console.log("updatestask"+tasktitle);
      
      this.dataservice.updateTaskByTaskTitle(this.taskDetails,boardName,stageName,tasktitle).subscribe({
        next:data=>{
          this.taskDetails=data;
          this.routerservice.navigateToBoardView(boardName);
        }
      })
    })
  }
  deletetask(){
    console.log("inside the delete task methos");
    this.activateroute.paramMap.subscribe((params)=>{
      let boardName = params.get('id')??""
      console.log("updatetask"+boardName);
      let stageName = params.get('id1')??""
      console.log("updatetask"+stageName);
      let tasktitle = params.get('id2')??""
      console.log("updatestask"+tasktitle);

      this.dataservice.deleteTask(boardName,stageName,tasktitle).subscribe({
        next:data=>{
          this.taskList=data;
          alert("Task deleted successfully")
        }
      })
    })
    

  }


}
