
package com.employeeasistance.employeeasistancemanagement.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AssistanceRequest {
    private UUID employeeId;
    private LocalDate date;
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
