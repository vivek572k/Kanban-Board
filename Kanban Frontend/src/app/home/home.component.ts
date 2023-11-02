import { Component, OnInit } from '@angular/core';
import { Board } from 'src/assets/model/board';
import { TeamMember } from 'src/assets/model/teamMember';
import { DataServiceService } from '../service/data-service.service';
import { RouterService } from '../service/router.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  longText = `Build a custom board to start managing your work right away.`;
  boards:Board[]=[];
  members:TeamMember[]=[]
  
  constructor(private dataservice:DataServiceService,private routerservice:RouterService,private activateroute:ActivatedRoute){}
  ngOnInit(): void {
    this.dataservice.getAllMembersByEmailId().subscribe({
      next:data=>{
        this.members=data;
      },
      // error:error=>{
      //   alert("something went wrong while fetching Member list")
      // }
      
    })

    this.dataservice.getAlUserBoardFromList().subscribe({
      next:data=>{
        this.boards=data;
        console.log(this.boards);
        
      },
      error:error=>{
        alert("something went wrong while fetching board details")
      }
    })
  }


  getAllBoard(){
    this.dataservice.getAlUserBoardFromList().subscribe({
      next:data=>{
        this.boards=data;
      },
      error:error=>{
        alert("something went wrong while fetching board details")
      }
    })
   
  }
}
