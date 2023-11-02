import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from 'src/assets/model/board';
import { Stage } from 'src/assets/model/stage';
import { Task } from 'src/assets/model/task';
import { TeamMember } from 'src/assets/model/teamMember';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  api_url = "http://localhost:9000/api/v2/user/"

  constructor(private http: HttpClient) { }

  getAllUserDetails(): Observable<any> {
    return this.http.get(this.api_url + 'userDetail')
  }

  getAlUserBoardFromList(): Observable<any> {
    return this.http.get(this.api_url + 'boards')
  }

  getAllMembersByEmailId(): Observable<any> {
    return this.http.get(this.api_url + 'members')
  }

  getAllStagesByBoardName(boardName: string): Observable<any> {
    console.log(boardName)
    return this.http.get(this.api_url + 'board/stages/' + boardName)
  }

  saveUserBoardToList(board: Board): Observable<any> {
    console.log(board);
    return this.http.post(this.api_url + 'board/saveBoard', board)
  }

  deleteBoard( boardName: string): Observable<any> {
    console.log("delete board method invoked");
    
    return this.http.delete(this.api_url + 'board/' + boardName)
  }

  getallTasksByStageName(boardName: string, stageName: string): Observable<any> {
    return this.http.get(this.api_url + 'stage/' + boardName +'/'+ stageName)
  }

  saveBoardStageToSet(stage: Stage, boardName: string): Observable<any> {
    console.log(boardName)
    return this.http.post(this.api_url + 'stage/saveStage/' + boardName, stage)
  }

  deleteStage(boardName: string, stageName: string): Observable<any> {
    return this.http.delete(this.api_url + 'stage/' + boardName +'/'+stageName)
  }

  getTaskByTaskName( boardName: string, stageName: string, taskTitle: string): Observable<any> {
    return this.http.get(this.api_url + 'task/' + boardName +'/'+ stageName +'/'+ taskTitle)
  }

  saveStageTaskToList(task: Task, stageName: string,assignee:string): Observable<any> {
    console.log("task object"+task);
    
    return this.http.post(this.api_url + 'task/saveTask/'+stageName+'/'+assignee, task)
  }

  deleteTask( boardName: string, stageName: string, taskTitle: string): Observable<any> {
    return this.http.delete(this.api_url + 'task/' + boardName +'/'+ stageName +'/'+ taskTitle)
  }
  getBoardByName(boardName:string):Observable<any>{
    console.log("dataservice"+boardName);
    
    return this.http.get(this.api_url+'board/getboard/'+boardName)
  }
  saveTeamMember(member:TeamMember ): Observable<any> {
    return this.http.post(this.api_url+'addTeamMembers',member)   
  }
  updateTaskByTaskTitle(task:Task,boardName:string,stageName:string,taskTitle:string):Observable<any>{
    return this.http.put(this.api_url+'task/'+boardName+'/'+stageName+'/'+taskTitle,task)
  }
  getUserName():Observable<any>{
    return this.http.get(this.api_url+'username')
  }
  updateBoard(boardName:string,board:Board):Observable<any>{
    return this.http.put(this.api_url+'board/update/'+boardName,board)
  }
}

