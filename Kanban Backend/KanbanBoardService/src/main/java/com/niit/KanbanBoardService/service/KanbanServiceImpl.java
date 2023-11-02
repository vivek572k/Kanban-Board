package com.niit.KanbanBoardService.service;

import com.niit.KanbanBoardService.KanbanBoardServiceApplication;
import com.niit.KanbanBoardService.domain.Board;
import com.niit.KanbanBoardService.domain.Kanban;
import com.niit.KanbanBoardService.domain.TeamMember;
import com.niit.KanbanBoardService.exception.TeamMemberAlreadyException;
import com.niit.KanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.KanbanBoardService.exception.UserNotFoundException;
import com.niit.KanbanBoardService.proxy.KanbanProxy;
import com.niit.KanbanBoardService.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.TabExpander;
import java.util.*;

@Service
public class KanbanServiceImpl implements KanbanService{

    private KanbanRepository kanbanRepository;
    private KanbanProxy kanbanProxy;
    @Autowired
    public KanbanServiceImpl(KanbanRepository kanbanRepository, KanbanProxy kanbanProxy){
        this.kanbanRepository=kanbanRepository;
        this.kanbanProxy=kanbanProxy;
    }
    @Override
    public Kanban saveUser(Kanban user) throws UserAlreadyExistsException {
        if(kanbanRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }

       // return kanbanRepository.save(user);
         Kanban savedUser = kanbanRepository.save(user);
        if (!(savedUser.getEmailId().isEmpty())){
            ResponseEntity response =kanbanProxy.registerUser(user);
            System.out.println(response.getBody());
        }
        return savedUser;
    }

    @Override
    public List<Kanban> getAllUsers()throws UserNotFoundException {
        return kanbanRepository.findAll();
    }

    @Override
    public List<Board> getAllUserBoardsFromList(String emailId) throws UserNotFoundException {
        System.out.println(kanbanRepository.findById(emailId).get());
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return kanbanRepository.findById(emailId).get().getBoards();
    }

    @Override
    public Kanban saveTeamMembers(String emailId, TeamMember teamMember)throws UserNotFoundException,TeamMemberAlreadyException ,UserAlreadyExistsException{
        String defaultPassword="Abcd@123";
        if (kanbanRepository.findById(teamMember.getMember()).isEmpty()){
            Kanban newUser = new Kanban();
            newUser.setEmailId(teamMember.getMember());
            newUser.setPassword(defaultPassword);
            Kanban savedMember= kanbanRepository.save(newUser);
            if (!(savedMember.getEmailId().isEmpty())) {
                ResponseEntity response = kanbanProxy.registerUser(newUser);
                System.out.println(response.getBody());
            }
        }

            Kanban userdata = kanbanRepository.findById(emailId).get();
            System.out.println(userdata);
            if (userdata!=null) {
                if (teamMember.getMember().equals(emailId)) {
                    throw new UserAlreadyExistsException();
                }
                if (userdata.getTeamMembers() == null) {
                    userdata.setTeamMembers(Arrays.asList(teamMember));
                } else {
                    List<TeamMember> teamList = userdata.getTeamMembers();
                    teamList.add(teamMember);
                    userdata.setTeamMembers(teamList);

                }
            }
            return kanbanRepository.save(userdata);
    }

    @Override
    public List<TeamMember> getAllTeamMembersByemailId(String emailId) throws UserNotFoundException {
        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return kanbanRepository.findById(emailId).get().getTeamMembers();
    }

    @Override
    public String getUserNameByEmailId(String emailId) throws UserNotFoundException {

        if (kanbanRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return kanbanRepository.findById(emailId).get().getUserName();


    }


}
