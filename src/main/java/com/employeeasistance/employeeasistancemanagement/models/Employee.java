
package com.employeeasistance.employeeasistancemanagement.models;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;
    
    private boolean isActive;
    
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Assistance> employeeAssistances;

    public Employee() {
    }

    public Employee(UUID id, String name, String email, EmployeePosition position, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Assistance> getEmployeeAssistances() {
        return employeeAssistances;
    }

    public void setEmployeeAssistances(List<Assistance> employeeAssistances) {
        this.employeeAssistances = employeeAssistances;
    }
    
    
}
