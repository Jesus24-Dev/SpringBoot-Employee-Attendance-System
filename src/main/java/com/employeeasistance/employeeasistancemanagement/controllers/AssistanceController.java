
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.dtos.AssistanceRequest;
import com.employeeasistance.employeeasistancemanagement.dtos.AssistanceResponse;
import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.services.AssistanceService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {
    
    private final AssistanceService assistanceService;
    
    @Autowired
    public AssistanceController(AssistanceService assistanceService){
        this.assistanceService = assistanceService;
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AssistanceResponse>> getAllAsistancesByEmployee(@PathVariable UUID employeeId){
        List<Assistance> employeeAssistances = assistanceService.getAllAssistancesByEmployee(employeeId);       
        List<AssistanceResponse> assistancesResponse = employeeAssistances.stream().map(AssistanceResponse::new).toList();
        return ResponseEntity.ok(assistancesResponse);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<AssistanceResponse>> getAllAssistancesByDate(@PathVariable LocalDate date){
        List<Assistance> assistancesByDate = assistanceService.getAllAssistancesByDate(date);
        List<AssistanceResponse> assistancesResponse = assistancesByDate.stream().map(AssistanceResponse::new).toList();
        
        return ResponseEntity.ok(assistancesResponse);
    }
    
    @GetMapping("/between/{startDate}/{finishDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AssistanceResponse>> getAllAssistancesBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate finishDate){
        return ResponseEntity.ok(assistanceService.getAllAssistancesBetweenDates(startDate, finishDate).stream().map(AssistanceResponse::new).toList());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<AssistanceResponse> createAssistance(@RequestBody @Valid AssistanceRequest assistance){
        Assistance assistanceCreated = assistanceService.createAssistance(assistance.getEmployeeId(), assistance.getDate(), assistance.getEntryTime(), assistance.getDepartureTime());
    
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssistanceResponse(assistanceCreated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/departure/{id}")
    public ResponseEntity<AssistanceResponse> setDepartureTime(@PathVariable UUID id, @Valid @RequestBody LocalTime departureTime){
        Assistance assistanceDepartureUpdated = assistanceService.setDepartureTime(id, departureTime);
    
        return ResponseEntity.ok(new AssistanceResponse(assistanceDepartureUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/update/date/{id}")
    public ResponseEntity<AssistanceResponse> updateDate(@PathVariable UUID id, @Valid @RequestBody LocalDate date){
        Assistance assistanceDepartureUpdated = assistanceService.updateAssistanceDate(id, date);
    
        return ResponseEntity.ok(new AssistanceResponse(assistanceDepartureUpdated));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PatchMapping("/update/entry-time/{id}")
    public ResponseEntity<AssistanceResponse> updateEntryTime(@PathVariable UUID id, @Valid @RequestBody LocalTime entryTime){
        Assistance assistanceDepartureUpdated = assistanceService.updateAssistanceEntryTime(id, entryTime);
    
        return ResponseEntity.ok(new AssistanceResponse(assistanceDepartureUpdated));
    }
    
    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssistance(@PathVariable UUID id){
        assistanceService.deleteAssistance(id);
        
        return ResponseEntity.noContent().build();
    }
}
