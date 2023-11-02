import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { RouterService } from '../service/router.service';
import { Board } from 'src/assets/model/board';
import { TeamMember } from 'src/assets/model/teamMember';
import { DataServiceService } from '../service/data-service.service';
import { SignUp } from 'src/assets/model/register';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  boards:Board[]=[];
  members:TeamMember[]=[]
  selectedBoard?:Board;
userName:any;

  private breakpointObserver = inject(BreakpointObserver);
  constructor(private auth:AuthService,private routerservice:RouterService,private dataservice:DataServiceService){
    this.getAllBoard();
  }
  ngOnInit(): void {
    this.dataservice.getAllMembersByEmailId().subscribe({
      next:data=>{
        this.members=data;
      },
      // error:error=>{
      //   alert("something went wrong while fetching Member list")
      // }
    })
  
    // this.getAllBoard();
    this.dataservice.getAlUserBoardFromList().subscribe({
      next:data=>{
        this.boards=data;
      },
      error:error=>{
        alert("something went wrong while fetching board details")
      }
    })
  }
  
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
    validateLogoutStatus(){
      this.auth.cleartoken();
      this.routerservice.navigateToLoginView();
    }
    isLogin(){
      return this.auth.getStatus();
      
    }

    gotoboard(board:Board):void{
      this.selectedBoard=board;
      console.log("selected boardin goto board"+this.selectedBoard.boardName);
      
      
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
      this.dataservice.getAllMembersByEmailId().subscribe({
        next:data=>{
          this.members=data;
        },
        error:error=>{
          alert("something went wrong while fetching Member list")
        }
      })
    }

    deleteBoard(boardName:string){
      // const boardName:any=this.selectedBoard?.boardName
      console.log("delete board method"+boardName);
      
      this.dataservice.deleteBoard(boardName).subscribe((boards)=>{
    
        this.boards=boards;
        alert("board deleted successfully")
        this.getAllBoard();
        
      })
    }
    
    
  }
