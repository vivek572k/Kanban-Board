package com.niit.KanbanBoardService.controller;

import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.TeamMember;
import com.niit.KanbanBoardService.exception.TeamMemberAlreadyException;
import com.niit.KanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;
import com.niit.KanbanBoardService.service.KanbanService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2/")
public class KanbanController {
    private KanbanService kanbanService;
    private ResponseEntity responseEntity;
    @Autowired
    public KanbanController(KanbanService kanbanService){
    this.kanbanService=kanbanService;
    }
    @PostMapping("save")
    public ResponseEntity<?>saveUser(@RequestBody Kanban user) throws UserAlreadyExistsException{
        try {
            responseEntity = new ResponseEntity<>(kanbanService.saveUser(user), HttpStatus.OK);
        }
        catch (UserAlreadyExistsException exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }
    @GetMapping("user/userDetail")
    public ResponseEntity getAllUserDetails()throws UserNotFoundException{
        try{
            responseEntity = new ResponseEntity(kanbanService.getAllUsers(),HttpStatus.OK);
        }
        catch (UserNotFoundException exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
   @GetMapping("user/boards")
    public ResponseEntity<?> getAllBoards(HttpServletRequest request) throws UserNotFoundException {
        // display all the boards of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try{

            System.out.println("header" +request.getHeader("Authorization"));
             Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: "+emailId);
            responseEntity=new ResponseEntity<>(kanbanService.getAllUserBoardsFromList(emailId),HttpStatus.OK);
        }catch (Exception exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }
    @GetMapping("user/members")
    public ResponseEntity<?> getAllTeamMembers(HttpServletRequest request) throws UserNotFoundException {
        // display all the boards of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: "+emailId);
            responseEntity=new ResponseEntity<>(kanbanService.getAllTeamMembersByemailId(emailId),HttpStatus.OK);
        }catch (Exception exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }
    @GetMapping("user/username")
    public ResponseEntity<?> getUserName(HttpServletRequest request) throws UserNotFoundException {
        // display all the boards of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: "+emailId);
            responseEntity=new ResponseEntity<>(kanbanService.getUserNameByEmailId(emailId),HttpStatus.OK);
        }catch (Exception exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }
    @PostMapping("user/addTeamMembers")
    public ResponseEntity<?> saveTeamMembers(@RequestBody TeamMember teamMember, HttpServletRequest request) throws UserNotFoundException,TeamMemberAlreadyException,UserAlreadyExistsException{
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: "+emailId);
            responseEntity = new ResponseEntity<>(kanbanService.saveTeamMembers(emailId,teamMember),HttpStatus.OK);
        }
        catch (UserNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        catch (TeamMemberAlreadyException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new TeamMemberAlreadyException();
        }
        catch (UserAlreadyExistsException exception){
            responseEntity=new ResponseEntity(exception.getMessage(),HttpStatus.CONFLICT);
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }
}
