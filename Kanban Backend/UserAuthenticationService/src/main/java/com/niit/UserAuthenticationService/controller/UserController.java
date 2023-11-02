package com.niit.UserAuthenticationService.controller;

import com.niit.UserAuthenticationService.domain.User;
import com.niit.UserAuthenticationService.exception.InvalidCredentialsException;
import com.niit.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.UserAuthenticationService.security.SecurityTokenGenerator;
import com.niit.UserAuthenticationService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private UserServiceImpl userService;
    private ResponseEntity responseEntity;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserServiceImpl userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("register")
    public ResponseEntity saveUser(@RequestBody User user)throws UserAlreadyExistsException {
        try{
            responseEntity=new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException exception){
            responseEntity=new ResponseEntity(exception.getMessage(),HttpStatus.CONFLICT);
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user)throws InvalidCredentialsException {

            User userData = userService.getUserByemailIdAndPassword(user.getEmailId(), user.getPassword());
            if (userData==null) {
                throw new InvalidCredentialsException();
            }
            String token = securityTokenGenerator.createToken(user);
             System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
