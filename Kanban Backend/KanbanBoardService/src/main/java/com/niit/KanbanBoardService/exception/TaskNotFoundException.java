package com.niit.KanbanBoardService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Task not Found")
public class TaskNotFoundException extends Exception{
}
