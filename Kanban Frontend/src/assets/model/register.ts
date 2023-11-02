import { Board } from "./board"
import { TeamMember } from "./teamMember"

export type SignUp={
    emailId:string,
    password:string,
    userName:string,
    boards:Board[],
    TeamMembers:TeamMember[]
}