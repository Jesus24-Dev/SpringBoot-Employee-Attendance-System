
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import com.employeeasistance.employeeasistancemanagement.models.User;
import java.util.UUID;

public class UserResponse {
    private final UUID id;
    private final String username;
    private final UserRoles role;
    private final EmployeeMinResponse employee;
    
    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.employee = user.getEmployee() != null ? new EmployeeMinResponse(user.getEmployee()) : null;
    }

    public String getUsername() {
        return username;
    }

    public UserRoles getRole() {
        return role;
    }
    
    public UUID getId(){
        return id;
    }
    
    public EmployeeMinResponse getEmployee(){
        return employee;
    }
}
