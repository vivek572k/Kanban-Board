package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.domain.Task;
import com.niit.KanbanBoardService.exception.*;
import com.niit.KanbanBoardService.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService{

   private KanbanRepository kanbanRepository;

    @Autowired
    public TaskServiceImpl(KanbanRepository kanbanRepository) {
        this.kanbanRepository = kanbanRepository;
    }
    @Override
    public Task getTaskByTaskName(String emailId, String boardName, String stageName, String taskTitle) throws TaskNotFoundException,StageNotFoundException,BoardNotFoundException,UserNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        Kanban userData = kanbanRepository.findById(emailId).get();
        List<Board> boardList = userData.getBoards();
        for (Board board : boardList) {
            if (board.getBoardName().equals(boardName)) {
                Set<Stage> stageData = board.getStages();
                for (Stage stage : stageData) {
                    if (stage.getStageName().equals(stageName)) {
                        List<Task> taskList = stage.getTaskList();
                        for (Task task : taskList) {
                            if (task.getTaskTitle().equals(taskTitle)) {
                                return task;
                            }
                        }

                        throw new TaskNotFoundException();
                    }
                }

                throw new StageNotFoundException();
            }
        }

        throw new BoardNotFoundException();
    }

    @Override
    public boolean saveStageTaskToList(Task task, String stageName, String emailId,String assignee) throws UserNotFoundException,TaskAlreadyExistException,MoreThanThreeTaskException{
        Boolean booleanvalue=false;
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        int count = 0;
        Kanban user = kanbanRepository.findById(emailId).get();
        List<Board>boardList=user.getBoards();
        for (Board board:boardList) {
            Set<Stage>stageSet=board.getStages();
            for (Stage stage:stageSet) {
                List<Task> tlist=stage.getTaskList();
                for(Task t:tlist){
                    if(t.getAssignee().equals(assignee)){
                        count++;
                    }
                if(count>3){
                    throw new MoreThanThreeTaskException();
                }
                if(stage.getStageName().equals(stageName)){
                    if (stage.getTaskList()==null){
                        System.out.println("entered in if condition");
                        stage.setTaskList(Arrays.asList(task));
                    }
                    else {
                        List<Task>taskList =stage.getTaskList();
                        for (Task task1:taskList) {
//                            List<Task> taskForMember=taskList.g;
                            if (task1.getTaskTitle().equals(task.getTaskTitle())){
                                throw new TaskAlreadyExistException();
                            }
                            else {
                                taskList.add(task);
                                stage.setTaskList(taskList);
//                        }
                                booleanvalue=true;
                            }
//                            if (task1.getAssignee().equals(assignee)){
//                                count++;
//                            }
                        }
//                        System.out.println(count);
//                        if (count<3) {
//                            taskList.add(task);
//                            stage.setTaskList(taskList);
//                        }
//                            count++;

                    }
                    kanbanRepository.save(user);

                    }

                }
            }

        }
        return booleanvalue;
    }

    @Override
    public Kanban deleteTask(String emailId, String boardName, String stageName, String taskName) throws UserNotFoundException, TaskNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        Kanban user = kanbanRepository.findById(emailId).get();
        List<Board> boardList = user.getBoards();
        for (Board board : boardList) {
            if (board.getBoardName().equals(boardName)) {
                Set<Stage> stageSet = board.getStages();
                for (Stage stage : stageSet) {
                    if (stage.getStageName().equals(stageName)) {
                        List<Task> tasks = stage.getTaskList();
                        boolean removed = tasks.removeIf(task -> task.getTaskTitle().equals(taskName));
                        if (!removed) {
                            throw new TaskNotFoundException();
                        }
                    }
                }
            }
        }
        return kanbanRepository.save(user);
    }

    @Override
    public Kanban updateTask(String emailId,String boardName, String stageName, String taskTitle, Task task) throws UserNotFoundException, StageNotFoundException, TaskNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        Kanban user = kanbanRepository.findById(emailId).get();
        List<Board>boardList=user.getBoards();
        for (Board board:boardList) {
            Set<Stage>stageSet=board.getStages();
            for (Stage stage:stageSet) {
                if(stage.getStageName().equals(stageName)) {
                    if (stage.getStageName().equals(task.getStatus())) {
                        List<Task> taskList = stage.getTaskList();
                        List<Task> newTaskList = new ArrayList<>();
                        System.out.println("task update");
                        for (Task task1 : taskList) {
                            if (Objects.equals(task1.getTaskTitle(), task.getTaskTitle())) {
                                newTaskList.add(task);
                            } else {
                                newTaskList.add(task1);
                            }
                        }
                        stage.setTaskList(newTaskList);
                    }
                }
                board.setStages(stageSet);
            }
            user.setBoards(boardList);
        }
        return kanbanRepository.save(user);
    }
}

