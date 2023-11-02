import { Component, Input, OnInit } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TeamMember } from 'src/assets/model/teamMember';
import { Task } from 'src/assets/model/task';
import { Stage } from 'src/assets/model/stage';
import { Board } from 'src/assets/model/board';
import { CanComponentDeactivate } from '../deactivate.guard';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit,CanComponentDeactivate {
  teamMember: TeamMember[] = []
  taskList:Task[]=[]
  stage:Stage={
    stageName: ''
  }
  submitStatus:boolean=false
 stageList:any
  date: Date = new Date();
  constructor(private dataservice: DataServiceService, private routerservice: RouterService, private form: FormBuilder, private activateroute: ActivatedRoute) { }
  ngOnInit(): void {
    this.dataservice.getAllMembersByEmailId().subscribe({
      next: data => {
        this.teamMember = data;
      },
      error: error => {
        alert("something went wrong while fetching Member list")
      }

    })

    this.activateroute.paramMap.subscribe((params:any)=>{
      let boardName = params.get('id')??""
    
   this.dataservice.getAllStagesByBoardName(boardName).subscribe({
        next: data => {
          this.stageList = data;
        },
        error: error => {
          alert('something went wrong while fetching stages')
        }
      })
    })

  }
  taskForm = this.form.group({
    taskTitle: ["", Validators.required],
    description: ["", Validators.required],
    assignee: ["", Validators.required],
    deadline: ["", Validators.required],
    status: ["", Validators.required],
    priority: ["", Validators.required]
  })
  get taskTitle() { return this.taskForm.get("taskTitle") }
  get description() { return this.taskForm.get("description") }
  get assignee() { return this.taskForm.get("assignee") }
  get deadline() { return this.taskForm.get("deadline") }
  get status() { return this.taskForm.get("status") }
  get priority() { return this.taskForm.get("priority") }



  saveTask() {

    const task: Task = this.taskForm.value as Task

   const assignee:any = task.assignee
    
    this.activateroute.paramMap.subscribe((params:any)=>{
      let stageName = params.get('id1')??""
      console.log("saveStage"+stageName);
      let boardName = params.get('id')??""
      console.log("board"+boardName);
      
        this.dataservice.saveStageTaskToList(task, stageName,assignee).subscribe( {
       next:data=>{
        this.taskList = data;
          console.log("saving a task" + stageName);
          this.submitStatus=true;
          this.routerservice.navigateToBoardView(boardName);
       },
          
       error:error=>{
        alert("More than 3 tasks cannot be assigne to a single member")
        
       }
        
          
        });
      })
      }

      canDeactivate():any{
        if (!this.submitStatus) {
          this.submitStatus = confirm("Are you sure to leave the page,the details you have entered is not stored. Click 'OK' to exit (or) 'Cancel' to continue");
          return this.submitStatus;
        }
        return true
      }

    }
