
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.dtos.UserRequest;
import com.employeeasistance.employeeasistancemanagement.dtos.UserResponse;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        User userCreated = userService.createUser(request.getUsername(), request.getPassword(), request.getRole());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(userCreated));
    }
}
