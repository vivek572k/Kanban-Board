package com.niit.KanbanBoardService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "team member already exists")
public class TeamMemberAlreadyException extends Exception {
}
