package com.niit.KanbanBoardService.controller;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.exception.*;
import com.niit.KanbanBoardService.service.StageService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v2/user/stage/")
public class StageController {
    private StageService stageService;
    private ResponseEntity responseEntity;

    @Autowired

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping("saveStage/{boardName}")
    public ResponseEntity<?> saveStage(@RequestBody Stage stage,@PathVariable String boardName, HttpServletRequest request) throws  UserNotFoundException,StageAlreadyExistsException {
        // add a stage to a specific board, return 201 status if stage is saved else 500 status
        System.out.println("boarname is printing in save stage method"+boardName);
        System.out.println("stage object is printing in save stage method"+stage);
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String emailId = claims.getSubject();
            responseEntity = new ResponseEntity<>(stageService.saveBoardStagesToSet(stage, boardName, emailId), HttpStatus.CREATED);
            System.out.println();
        } catch (StageAlreadyExistsException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
            throw  new StageAlreadyExistsException();
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("{boardName}/{stageName}")
    public ResponseEntity<?> deleteStage(@PathVariable String stageName, @PathVariable String boardName, HttpServletRequest request) throws BoardNotFoundException, UserNotFoundException ,StageNotFoundException{
        // delete a stage based on  email id , board id,and stageId extract user id from claims
        // return 200 status if user is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("emailId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(stageService.deleteStage(emailId, boardName, stageName), HttpStatus.OK);
        } catch (StageNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new StageNotFoundException();
        }
        catch (BoardNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("{boardName}/{stageName}")
    public ResponseEntity<?> getAllTasksByStageName(HttpServletRequest request,@PathVariable String boardName,@PathVariable String stageName) throws UserNotFoundException,StageNotFoundException,BoardNotFoundException {

        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: "+emailId);
            responseEntity=new ResponseEntity<>(stageService.getAllTaskByStageName(emailId,boardName,stageName),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        catch (BoardNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        }
        catch (StageNotFoundException exception){
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new StageNotFoundException();
        }
        // return 200 status if user is saved else 500 status
        return responseEntity;
    }
}