package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.TeamMember;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.TeamMemberAlreadyException;
import com.niit.KanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface KanbanService {
    Kanban saveUser(Kanban user) throws UserAlreadyExistsException;
    List<Kanban> getAllUsers()throws UserNotFoundException;
    List<Board>getAllUserBoardsFromList(String emailId) throws UserNotFoundException;
    Kanban saveTeamMembers(String emailId, TeamMember teamMember)throws UserNotFoundException,TeamMemberAlreadyException,UserAlreadyExistsException;
    List<TeamMember> getAllTeamMembersByemailId(String emailId) throws UserNotFoundException;

    String getUserNameByEmailId(String emailId)throws UserNotFoundException;
}
