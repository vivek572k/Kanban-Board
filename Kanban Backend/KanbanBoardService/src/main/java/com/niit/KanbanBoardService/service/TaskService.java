package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.domain.Task;
import com.niit.KanbanBoardService.exception.*;

public interface TaskService {
    Task getTaskByTaskName(String emailId,String boardName, String stageName,String taskTitle) throws TaskNotFoundException,StageNotFoundException,BoardNotFoundException,UserNotFoundException;
    boolean saveStageTaskToList(Task task, String stageName, String emailId,String assignee) throws  UserNotFoundException, TaskAlreadyExistException,MoreThanThreeTaskException;
    Kanban deleteTask(String emailId,String boardName,String stageName,String taskName) throws UserNotFoundException,TaskNotFoundException;

    Kanban updateTask(String emailId,String boardName,String stageName,String taskTitle,Task task) throws UserNotFoundException,StageNotFoundException,TaskNotFoundException;
    
}
