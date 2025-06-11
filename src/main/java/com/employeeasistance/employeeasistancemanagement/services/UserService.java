
package com.employeeasistance.employeeasistancemanagement.services;

import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.repositories.UserRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public User getUserById(UUID id){
        User userFounded = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not founded: " + id));
        
        return userFounded;
    }
    
    public User createUser(String username, String password, UserRoles role){
        User userToSave = new User();
        userToSave.setUsername(username);
        userToSave.setPassword(passwordEncoder.encode(password));
        userToSave.setRole(role);
        
        return userRepository.save(userToSave);
    }
    
    public User updateUser(UUID id, String username, String password, UserRoles role){
        User userToUpdate = getUserById(id);
        
        userToUpdate.setUsername(username);
        userToUpdate.setPassword(passwordEncoder.encode(password));
        userToUpdate.setRole(role);
        
        return userRepository.save(userToUpdate);
    }
    
    public void deleteUser(UUID id){
        User userToDelete = getUserById(id);
        
        userRepository.delete(userToDelete);
    }
}
