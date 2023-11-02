package com.niit.KanbanBoardService.controller;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.exception.BoardAlreadyExistException;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;
import com.niit.KanbanBoardService.service.BoardService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2/user/board/")
public class BoardController {
    private BoardService boardService;
    private ResponseEntity responseEntity;

    @Autowired

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("saveBoard")
    public ResponseEntity<?> saveBoard(@RequestBody Board board, HttpServletRequest request) throws BoardAlreadyExistException, UserNotFoundException {
        // add a board to a specific user, return 201 status if board is saved else 500 status
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String emailId = claims.getSubject();
            System.out.println(claims);
            System.out.println(emailId);
            responseEntity = new ResponseEntity<>(boardService.saveUserBoardtoList(board, emailId), HttpStatus.CREATED);
        } catch (BoardAlreadyExistException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardAlreadyExistException();
        }
        catch (UserNotFoundException exception){
            throw new  UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("{boardName}")
    public ResponseEntity<?> deleteBoard(@PathVariable String boardName, HttpServletRequest request) throws BoardNotFoundException, UserNotFoundException {
        // delete a board based on  email id and board id, extract user id from claims
        // return 200 status if user is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("emailId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(boardService.deleteBoard(emailId, boardName), HttpStatus.OK);
        } catch (BoardNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("stages/{boardName}")
    public ResponseEntity<?> getAllStagesByBoardName(@PathVariable String boardName,HttpServletRequest request) throws UserNotFoundException,BoardNotFoundException {

        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println(boardName);
            System.out.println("emailId :: "+emailId);
            responseEntity=new ResponseEntity<>(boardService.getAllStagesByBoardName(emailId,boardName),HttpStatus.OK);
            System.out.println(responseEntity);
        }
        catch (UserNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        catch (BoardNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }
    @GetMapping("getboard/{boardName}")
    public ResponseEntity<?> getBoardByName(@PathVariable String boardName,HttpServletRequest request) throws UserNotFoundException,BoardNotFoundException {

        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println(boardName);
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(boardService.getBoard(emailId, boardName), HttpStatus.OK);
            System.out.println(responseEntity);
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        } catch (BoardNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }

    @PutMapping("update/{boardName}")
    public ResponseEntity<?>updateBoard(@PathVariable String boardName,Board board,HttpServletRequest request) throws UserNotFoundException,BoardNotFoundException{
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println(boardName);
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(boardService.updateBoard(emailId, boardName,board), HttpStatus.OK);
            System.out.println(responseEntity);
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        } catch (BoardNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }

}