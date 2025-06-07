
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import java.util.UUID;

public class EmployeeMinResponse {
    private final UUID id;
    private final String name;
    private final EmployeePosition position;
    
    public EmployeeMinResponse(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.position = employee.getPosition();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeePosition getPosition() {
        return position;
    }
    
    
}
