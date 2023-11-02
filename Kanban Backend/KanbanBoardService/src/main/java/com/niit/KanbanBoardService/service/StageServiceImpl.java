package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.Stage;
import com.niit.KanbanBoardService.domain.Task;
import com.niit.KanbanBoardService.exception.BoardNotFoundException;
import com.niit.KanbanBoardService.exception.StageAlreadyExistsException;
import com.niit.KanbanBoardService.exception.StageNotFoundException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;
import com.niit.KanbanBoardService.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StageServiceImpl implements StageService{

    private KanbanRepository kanbanRepository;

@Autowired
    public StageServiceImpl(KanbanRepository kanbanRepository) {
        this.kanbanRepository=kanbanRepository;
    }


    @Override
    public List<Task> getAllTaskByStageName(String emailId, String boardName, String stageName) throws StageNotFoundException, BoardNotFoundException, UserNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        Kanban userData = kanbanRepository.findById(emailId).get();
        List<Board> boardList = userData.getBoards();
        Set<Stage> stageData = null;
        List<Task>taskList=null;
        for (Board board:boardList) {
            stageData=board.getStages();
            for (Stage stage:stageData) {
                if (stage.getStageName().equals(stageName)){
                    taskList=stage.getTaskList();
                    return taskList;
                }
            }
            throw new StageNotFoundException();
        }
        throw new BoardNotFoundException();
    }

    @Override
    public Kanban saveBoardStagesToSet(Stage stage, String boardName, String emailId) throws UserNotFoundException,StageAlreadyExistsException {
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        Kanban user = kanbanRepository.findById(emailId).get();
//        System.out.println(user);
       List<Board>boardList= user.getBoards();
        System.out.println("trying to add stages");
//        Board boardData = kanbanRepository.findBoardByBoardName(boardName).get();
        for (Board board:boardList) {
            System.out.println(board.getBoardName());
            if(board.getBoardName().equals(boardName)){
               if (board.getStages()==null){
                   System.out.println("entered in if condition");
                   Set<Stage> stages = new HashSet<>();
                   stages.add(stage);
                   board.setStages(stages);
                   System.out.println(board.getStages());
               }
               else {
                   System.out.println("entered in else part");
                   Set<Stage> stages = board.getStages();
                   for (Stage stage1:stages) {
                       if (stage1.getStageName().equals(stage.getStageName())){
                           throw new StageAlreadyExistsException();
                       }
                   }
                   stages.add(stage);
                   board.setStages(stages);

               }
                user.setBoards(boardList);
            }
        }
        return kanbanRepository.save(user);
    }


    @Override
    public Kanban deleteStage(String emailId, String boardName, String stageName) throws UserNotFoundException, StageNotFoundException {
        boolean stageIdIsPresent=false;
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();

        }
        Kanban user =kanbanRepository.findById(emailId).get();
        List<Board>boardList=user.getBoards();
        for (Board board:boardList) {
            if (board.getBoardName().equals(boardName)){
                Set<Stage>stages = board.getStages();
                stageIdIsPresent=stages.removeIf(stage -> stage.getStageName().equals(stageName));
                board.setStages(stages);
            }
            if (!stageIdIsPresent){
                throw new StageNotFoundException();
            }
            user.setBoards(boardList);
    }
    return kanbanRepository.save(user);
    }
}
