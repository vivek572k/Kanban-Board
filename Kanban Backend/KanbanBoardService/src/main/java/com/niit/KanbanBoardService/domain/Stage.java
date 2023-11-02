package com.niit.KanbanBoardService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class Stage {
    @Id
    private String stageName;

    private List<Task> taskList;


    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Stage{" +
                ", stageName='" + stageName + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
