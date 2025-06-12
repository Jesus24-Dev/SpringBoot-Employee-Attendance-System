
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import java.util.UUID;

public class EmployeeResponse {
    private final UUID id;
    private final String name;
    private final String email;
    private final EmployeePosition position;
    private final boolean isActive;
    
    public EmployeeResponse(Employee employee){
        this.id = employee.getId();
        this.name = employee.getEmail();
        this.email = employee.getName();
        this.position = employee.getPosition();
        this.isActive = employee.isIsActive();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public boolean isIsActive() {
        return isActive;
    }
    
    
}
