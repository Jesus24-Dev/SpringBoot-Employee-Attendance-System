
package com.employeeasistance.employeeasistancemanagement.dtos;

import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AssistanceResponse {
    private final UUID id;
    private final EmployeeMinResponse employee;
    private final LocalDate date;
    private final LocalTime entryTime;    
    private final LocalTime departureTime;
    
    public AssistanceResponse(Assistance assistance){
        this.id = assistance.getId();
        this.employee = new EmployeeMinResponse(assistance.getEmployee());
        this.date = assistance.getDate();
        this.entryTime = assistance.getEntryTime();
        this.departureTime = assistance.getDepartureTime();
    }

    public UUID getId() {
        return id;
    }

    public EmployeeMinResponse getEmployee() {
        return employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
    
}
