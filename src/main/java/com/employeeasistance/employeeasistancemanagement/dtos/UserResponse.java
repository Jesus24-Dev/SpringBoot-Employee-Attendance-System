
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import com.employeeasistance.employeeasistancemanagement.models.User;

public class UserResponse {
    private final String username;
    private final UserRoles role;
    
    public UserResponse(User user){
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public String getUsername() {
        return username;
    }

    public UserRoles getRole() {
        return role;
    }
    
    
}
