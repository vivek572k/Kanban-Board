package com.niit.KanbanBoardService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public class Task {
    @Id
    private String taskTitle;
    private String description;
    private String assignee;
    private String status;
    private Date deadline;
    private String priority;

    public Task( String taskTitle, String description, String assignee, String status, Date deadline, String priority) {

        this.taskTitle = taskTitle;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.deadline = deadline;
        this.priority = priority;
    }
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", assignee='" + assignee + '\'' +
                ", status='" + status + '\'' +
                ", deadline=" + deadline +
                ", priority='" + priority + '\'' +
                '}';
    }
}
