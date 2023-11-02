package com.niit.KanbanBoardService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Document
public class Kanban {
    @Id
    private String emailId;//email save kanban at one place
    private String password;
    private String userName;
    private List<Board> boards;
    private List<TeamMember> teamMembers;



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public String toString() {
        return "Kanban{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", kanBanName='" + userName + '\'' +
                ", boards=" + boards +
                ", teamMembers=" + teamMembers +
                '}';
    }
}
