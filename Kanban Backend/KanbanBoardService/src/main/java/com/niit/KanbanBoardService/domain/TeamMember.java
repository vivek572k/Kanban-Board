package com.niit.KanbanBoardService.domain;

import org.springframework.data.annotation.Id;

public class TeamMember {

    @Id
    String id;
    String member;
    String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "TeamMember{" +
                "id='" + id + '\'' +
                ", member='" + member + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
