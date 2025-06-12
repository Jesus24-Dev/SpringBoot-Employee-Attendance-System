
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {
    
    @Size(min = 5, message = "Insert a valid employee name.")
    @NotBlank(message = "Employee name is required.")
    private String name;
    
    @Email(message = "Insert a valid email")
    @NotBlank(message = "Employee email is required.")
    private String email;
    
    @NotNull(message = "Employee position is required.")
    private EmployeePosition position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }
    
       
}
