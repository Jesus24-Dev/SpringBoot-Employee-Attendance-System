
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;

public class EmployeeRequest {
    private String name;
    private String email;
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
