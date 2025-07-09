
package com.employeeasistance.employeeasistancemanagement.services;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.repositories.EmployeeRepository;
import com.employeeasistance.employeeasistancemanagement.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository){
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }
    
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
    
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(UUID id){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        return employeeFounded;
    }
    
    @Transactional
    public Employee createEmployee(String name, String email, EmployeePosition position){
               
        User authUser = getAuthenticatedUser();

        User currentUser = userRepository.findById(authUser.getId())
            .orElseThrow(() -> new RuntimeException("User not founded"));

        if (currentUser.getEmployee() != null) {
            throw new IllegalStateException("This user already have a employee profile.");
        }

        Employee employeeCreated = new Employee();
        employeeCreated.setName(name);
        employeeCreated.setEmail(email);
        employeeCreated.setPosition(position);
        employeeCreated.setIsActive(true);
        employeeCreated.setUser(currentUser);
        
        return employeeRepository.save(employeeCreated);
    }
      
    public Employee updateEmployeeName(UUID id, String name){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        employeeFounded.setName(name);
        
        return employeeRepository.save(employeeFounded);
    }
    
    public Employee updateEmployeeEmail(UUID id, String email){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        employeeFounded.setEmail(email);
        
        return employeeRepository.save(employeeFounded);
    }
    
    public Employee updateEmployeePosition(UUID id, EmployeePosition position){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        employeeFounded.setPosition(position);  
        
        return employeeRepository.save(employeeFounded);
    }
    
    public void deleteEmployee(UUID id){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        employeeFounded.setIsActive(false);
        employeeRepository.save(employeeFounded);
    }
}
