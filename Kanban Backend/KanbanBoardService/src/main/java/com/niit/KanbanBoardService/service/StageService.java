package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.domain.Task;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.StageAlreadyExistsException;
import com.niit.KanbanBoardService.exception.StageNotFoundException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;

import java.util.List;

public interface StageService {
    List<Task> getAllTaskByStageName(String emailId, String boardName , String stageName) throws StageNotFoundException,BoardNotFoundException,UserNotFoundException;
    Kanban saveBoardStagesToSet(Stage stage, String boardName, String emailId) throws  UserNotFoundException, StageAlreadyExistsException;
    Kanban deleteStage(String emailId,String boardName,String stageName) throws UserNotFoundException,BoardNotFoundException,StageNotFoundException;
}
