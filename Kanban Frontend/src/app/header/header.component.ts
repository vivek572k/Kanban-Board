import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { RouterService } from '../service/router.service';
import { DataServiceService } from '../service/data-service.service';
import { Board } from 'src/assets/model/board';
import { TeamMember } from 'src/assets/model/teamMember';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  boards:Board[]=[];
  members:TeamMember[]=[]
  longText = `Build a custom board to start managing your work right away.`;
  constructor(private auth:AuthService, private router:RouterService,private dataservice:DataServiceService){}
  ngOnInit(): void {}

  
  validateLogoutStatus(){
    this.auth.cleartoken();
    this.router.navigateToLoginView();
  }

}
