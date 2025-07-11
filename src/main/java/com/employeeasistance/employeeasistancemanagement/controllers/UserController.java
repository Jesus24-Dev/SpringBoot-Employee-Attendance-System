
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.dtos.UserRequest;
import com.employeeasistance.employeeasistancemanagement.dtos.UserResponse;
import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers().
                stream().
                map(UserResponse::new).
                toList());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id){
        User user = userService.getUserById(id);
        
        return ResponseEntity.ok(new UserResponse(user));
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request){
        User userCreated = userService.createUser(request.getUsername(), request.getPassword(), request.getRole());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(userCreated));
    }
       
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/update/name/{id}")
    public ResponseEntity<UserResponse> updateUserUsername(@PathVariable UUID id, @RequestBody @Valid String username){
        User userUpdated = userService.updateUserUsername(id, username);
        
        return ResponseEntity.ok(new UserResponse(userUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/update/password/{id}")
    public ResponseEntity<UserResponse> updateUserPassword(@PathVariable UUID id, @RequestBody @Valid String password){
        User userUpdated = userService.updateUserPassword(id, password);
        
        return ResponseEntity.ok(new UserResponse(userUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/update/role/{id}")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable UUID id, @RequestBody @Valid UserRoles role){
        User userUpdated = userService.updateUserRole(id, role);
        
        return ResponseEntity.ok(new UserResponse(userUpdated));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        
        return ResponseEntity.noContent().build();
    }
}
