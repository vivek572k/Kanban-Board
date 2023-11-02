package com.niit.KanbanBoardService.controller;
import com.niit.KanbanBoardService.domain.Task;
import com.niit.KanbanBoardService.exception.*;
import com.niit.KanbanBoardService.service.TaskService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2/user/task/")
public class TaskController {
    private TaskService taskService;
    private ResponseEntity responseEntity;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping("saveTask/{stageName}/{assignee}")
//    public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable String stageName, HttpServletRequest request,String assignee) throws UserNotFoundException, TaskAlreadyExistException,MoreThanThreeTaskException {
//        // add a stage to a specific board, return 201 status if stage is saved else 500 status
//        try {
//            Claims claims = (Claims) request.getAttribute("claims");
//            String emailId = claims.getSubject();
//            responseEntity = new ResponseEntity<>(taskService.saveStageTaskToList(task, stageName, emailId,assignee), HttpStatus.CREATED);
//        } catch (TaskAlreadyExistException exception) {
//            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
//            throw new TaskAlreadyExistException();
//        }
//        catch (MoreThanThreeTaskException exception){
//            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.CONFLICT);
//            throw new MoreThanThreeTaskException();
//        }
//        catch (UserNotFoundException exception) {
//            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
//            throw new UserNotFoundException();
//        }
//        return responseEntity;
//    }
    @PostMapping("saveTask/{stageName}/{assignee}")
    public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable String stageName,@PathVariable String assignee, HttpServletRequest request) throws UserNotFoundException, TaskAlreadyExistException,MoreThanThreeTaskException {
        // add a stage to a specific board, return 201 status if stage is saved else 500 status
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String emailId = claims.getSubject();
            responseEntity = new ResponseEntity<>(taskService.saveStageTaskToList(task, stageName, emailId, assignee), HttpStatus.CREATED);
        } catch (TaskAlreadyExistException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
            throw new TaskAlreadyExistException();
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
            throw new UserNotFoundException();
        } catch (MoreThanThreeTaskException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
            throw new MoreThanThreeTaskException();
        }
        return responseEntity;
    }

    @DeleteMapping("{boardName}/{stageName}/{taskName}")
    public ResponseEntity<?> deleteTaskByTaskId(@PathVariable String stageName, @PathVariable String boardName, @PathVariable String taskName, HttpServletRequest request) throws BoardNotFoundException, UserNotFoundException, StageNotFoundException, TaskNotFoundException {
        // delete a task based on  email id , board id, stageId and taskId extract user id from claims
        // return 200 status if user is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("emailId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(taskService.deleteTask(emailId, boardName, stageName, taskName), HttpStatus.OK);
        } catch (TaskNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new TaskNotFoundException();
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("{boardName}/{stageName}/{taskTitle}")
    public ResponseEntity<?> getTaskByTaskTitle(@PathVariable String boardName, @PathVariable String stageName, @PathVariable String taskTitle, HttpServletRequest request) throws UserNotFoundException, StageNotFoundException, BoardNotFoundException, TaskNotFoundException {

        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String emailId = claims.getSubject();
            System.out.println("emailId :: " + emailId);
            responseEntity = new ResponseEntity<>(taskService.getTaskByTaskName(emailId, boardName, stageName, taskTitle), HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new UserNotFoundException();
        } catch (BoardNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new BoardNotFoundException();
        } catch (StageNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new StageNotFoundException();
        } catch (TaskNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("{boardName}/{stageName}/{taskTitle}")
    public ResponseEntity<?> updateTaskByTaskTitle(@RequestBody Task task, @PathVariable String boardName,@PathVariable String stageName, @PathVariable String taskTitle, HttpServletRequest request) throws UserNotFoundException, StageNotFoundException,  TaskNotFoundException {
        System.out.println("task controller layer invoked");
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String emailId = claims.getSubject();
            responseEntity = new ResponseEntity<>(taskService.updateTask(emailId,boardName, stageName, taskTitle, task), HttpStatus.CREATED);

        } catch (TaskNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
            throw new TaskNotFoundException();
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        }
        catch (StageNotFoundException exception) {
            throw new StageNotFoundException();
        }
        return responseEntity;
    }
}