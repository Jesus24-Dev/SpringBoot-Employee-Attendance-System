
package com.employeeasistance.employeeasistancemanagement.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AssistanceRequest {
    
    @NotNull(message = "Employee ID is required for Assistance register.")
    private UUID employeeId;
    
    @NotNull(message = "Assistance date is required.")
    private LocalDate date;
    
    @NotNull(message = "Assistance entryTime is required.")
    @PastOrPresent(message = "You cannot register future attendance.")
    private LocalTime entryTime;    
    private LocalTime departureTime;

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    
    
}
