
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.enums.UserRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    
    @NotNull(message = "User Role is required")
    private UserRoles role;
    
    @Size(min = 5, max = 16, message = "Insert a valid username.")
    @NotBlank(message = "Username can't be empty.")
    private String username;
    
    @Size(min = 5, message = "Insert a valid password.")
    @NotBlank(message = "Password can't be empty.")
    private String password;

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
         
}
