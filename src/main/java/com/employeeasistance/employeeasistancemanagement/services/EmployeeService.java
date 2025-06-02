
package com.employeeasistance.employeeasistancemanagement.services;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.repositories.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(UUID id){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        return employeeFounded;
    }
    
    public void createEmployee(String name, String email, EmployeePosition position){
        Employee employeeCreated = new Employee();
        
        employeeCreated.setName(name);
        employeeCreated.setEmail(email);
        employeeCreated.setPosition(position);
        employeeCreated.setIsActive(true);
        
        employeeRepository.save(employeeCreated);
    }
    
    public void updateEmployee(UUID id, String name, String email, EmployeePosition position){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        employeeFounded.setName(name);
        employeeFounded.setEmail(email);
        employeeFounded.setPosition(position);  
        employeeRepository.save(employeeFounded);
    }
    
    public void deleteEmployee(UUID id){
        Employee employeeFounded = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        employeeFounded.setIsActive(false);
        employeeRepository.save(employeeFounded);
    }
}
