
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.services.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService; 
    
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable UUID id){
        Employee employee = employeeService.getEmployeeById(id);
                
        return ResponseEntity.ok(employee);
    }
    
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee.getName(), employee.getEmail(), employee.getPosition());
        
        return ResponseEntity.status(HttpStatus.CREATED).body("employee created");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee){
        employeeService.updateEmployee(id, employee.getName(), employee.getEmail(), employee.getPosition());
        
        return ResponseEntity.ok("employee updated");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
        
        return ResponseEntity.noContent().build();
    }
}
