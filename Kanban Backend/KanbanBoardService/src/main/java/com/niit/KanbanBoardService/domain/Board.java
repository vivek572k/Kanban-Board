package com.niit.KanbanBoardService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {

    @Id
    private String boardName;
    private Set<Stage> stages = new HashSet<>();

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public Board(){
    }

    @Override
    public String toString() {
        return "Board{" +
                ", boardName='" + boardName + '\'' +
                ", stages=" + stages +
                '}';
    }
}
