import { Component } from '@angular/core';
import { DataServiceService } from '../service/data-service.service';
import { FormBuilder, Validators } from '@angular/forms';
import { TeamMember } from 'src/assets/model/teamMember';
import { RouterService } from '../service/router.service';
import { CanComponentDeactivate } from '../deactivate.guard';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements CanComponentDeactivate{
  submitStatus:boolean=false
  constructor(private form: FormBuilder, private dataservice: DataServiceService, private routerservice: RouterService) { }

  memberForm = this.form.group({
    id: ["", Validators.required],
    member: ["", [Validators.required,Validators.pattern("^[a-zA-Z_.+-]+[a-zA-Z0-9_.+-]+@[a-zA-Z-]+\.[a-zA-Z-.]+$")]]
  })
  get id() { return this.memberForm.get("id") }
  get member() { return this.memberForm.get("member") }

  saveMember() {
    const member: TeamMember = this.memberForm.value as TeamMember
    this.dataservice.saveTeamMember(member).subscribe({
      next: data => {
        alert("Members saved successfully")
        this.submitStatus=true;
        this.routerservice.navigateToHomeView();
      }
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
