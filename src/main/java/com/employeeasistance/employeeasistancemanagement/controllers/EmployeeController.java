
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.dtos.EmployeeRequest;
import com.employeeasistance.employeeasistancemanagement.dtos.EmployeeResponse;
import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.services.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService; 
    
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        
        return ResponseEntity.ok(employees.stream().map(EmployeeResponse::new).toList());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id){
        Employee employee = employeeService.getEmployeeById(id);
                
        return ResponseEntity.ok(new EmployeeResponse(employee));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employee){
        Employee employeeCreated = employeeService.createEmployee(employee.getName(), employee.getEmail(), employee.getPosition());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeResponse(employeeCreated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")   
    @PatchMapping("/update/username/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeName(@PathVariable UUID id, @RequestBody @Valid String name){
        Employee employeeUpdated = employeeService.updateEmployeeName(id, name);
        
        return ResponseEntity.ok(new EmployeeResponse(employeeUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")   
    @PatchMapping("/update/email/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeEmail(@PathVariable UUID id, @RequestBody @Valid String email){
        Employee employeeUpdated = employeeService.updateEmployeeEmail(id, email);
        
        return ResponseEntity.ok(new EmployeeResponse(employeeUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")   
    @PatchMapping("/update/position/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeePosition(@PathVariable UUID id, @RequestBody @Valid EmployeePosition position){
        Employee employeeUpdated = employeeService.updateEmployeePosition(id, position);
        
        return ResponseEntity.ok(new EmployeeResponse(employeeUpdated));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
        
        return ResponseEntity.noContent().build();
    }
    
}
