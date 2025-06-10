
package com.employeeasistance.employeeasistancemanagement.services;

import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.repositories.UserRepository;
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
    
    public User createUser(String username, String password, UserRoles role){
        User userToSave = new User();
        userToSave.setUsername(username);
        userToSave.setPassword(passwordEncoder.encode(password));
        userToSave.setRole(role);
        
        return userRepository.save(userToSave);
    }
}
