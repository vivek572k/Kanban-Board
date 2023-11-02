import { Component, Input } from '@angular/core';
import { Board } from 'src/assets/model/board';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent {


  @Input() boards:any

  
  
}
