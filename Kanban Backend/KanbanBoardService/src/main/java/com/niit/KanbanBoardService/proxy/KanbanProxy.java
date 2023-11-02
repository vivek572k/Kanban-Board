package com.niit.KanbanBoardService.proxy;

import com.niit.KanbanBoardService.domain.Kanban;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-authentication-service",url = "localhost:8086")
public interface KanbanProxy {
    @PostMapping("/api/v1/register")
    public ResponseEntity<?>registerUser(@RequestBody Kanban user);

}
