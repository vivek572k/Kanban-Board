package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.exception.BoardAlreadyExistException;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;
import com.niit.KanbanBoardService.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService{
    private KanbanRepository kanbanRepository;
@Autowired
    public BoardServiceImpl(KanbanRepository kanbanRepository) {
        this.kanbanRepository=kanbanRepository;
    }

    @Override
    public Set<Stage> getAllStagesByBoardName(String emailId , String boardName) throws BoardNotFoundException, UserNotFoundException {
        System.out.println(emailId+boardName);
    System.out.println(kanbanRepository.findById(emailId).get());
        if (kanbanRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        Kanban userData = kanbanRepository.findById(emailId).get();
        System.out.println(userData);
        List<Board> boardList = userData.getBoards();
        Set<Stage> stageData = null;
        for (Board board : boardList) {
            if (board.getBoardName().equals(boardName)) {
                stageData = board.getStages();
                return stageData;
            }
        } // If no matching board is found, throw a BoardNotFoundException
            throw new BoardNotFoundException();
    }
    @Override
    public Kanban saveUserBoardtoList(Board board, String emailId) throws BoardAlreadyExistException, UserNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()){// Save the boards to the user.
            throw new UserNotFoundException();
        }
        Kanban userData = kanbanRepository.findById(emailId).get();
        if (userData.getBoards()==null){
            userData.setBoards(Arrays.asList(board));
        }
        else {
            List<Board> boardList = userData.getBoards();
            for (Board board1:boardList) {
                if (board1.getBoardName().equals(board.getBoardName())){
                    throw new BoardAlreadyExistException();
                }
            }
            boardList.add(board);
            userData.setBoards(boardList);
        }
        return kanbanRepository.save(userData);
    }

    @Override
    public Kanban deleteBoard(String emailId, String boardName) throws UserNotFoundException, BoardNotFoundException {
        boolean boardIdIsPresent=false;
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        Kanban user =kanbanRepository.findById(emailId).get();
        List<Board> boards = user.getBoards();
        boardIdIsPresent=boards.removeIf(x->x.getBoardName().equals(boardName));
        if (!boardIdIsPresent){
            throw new BoardNotFoundException();
        }
        user.setBoards(boards);
        return kanbanRepository.save(user);

    }

    @Override
    public Board getBoard(String emailId, String boardName) throws UserNotFoundException, BoardNotFoundException {
    if (kanbanRepository.findById(emailId).isEmpty()){
        throw new UserNotFoundException();
    }
    Kanban usedata= kanbanRepository.findById(emailId).get();
    List<Board> boardList=usedata.getBoards();
        for (Board board:boardList
             ) {
            if (board.getBoardName().equals(boardName)){
                return board;
            }
        }
        throw new BoardNotFoundException();
    }

    @Override
    public Board updateBoard(String emailId, String boardName,Board board) throws UserNotFoundException, BoardNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        Kanban userdata = kanbanRepository.findById(emailId).get();
        List<Board> boardList = userdata.getBoards();

        boolean boardFound = false;
        for (Board board1 : boardList) {
            if (board1.getBoardName().equalsIgnoreCase(boardName)) {
                if (board.getBoardName() != null) {
                    board1.setBoardName(board.getBoardName());
                }
                if (board.getStages() != null) {
                    board1.setStages(board.getStages());
                }
                kanbanRepository.save(userdata);
                return board;
            }
        }
        throw new BoardNotFoundException();
    }
}


