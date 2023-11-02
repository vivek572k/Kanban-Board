package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.exception.BoardAlreadyExistException;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;

import java.util.Set;

public interface BoardService {

    Set<Stage> getAllStagesByBoardName(String emailId, String boardName) throws BoardNotFoundException,UserNotFoundException;
    Kanban saveUserBoardtoList(Board board, String emailId) throws BoardAlreadyExistException, UserNotFoundException;
    Kanban deleteBoard(String emailId,String boardName) throws UserNotFoundException,BoardNotFoundException;
    Board getBoard(String emailId,String boardName)throws UserNotFoundException,BoardNotFoundException;
    Board updateBoard(String emailId, String boardName,Board board) throws UserNotFoundException,BoardNotFoundException;
}