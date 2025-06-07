
package com.employeeasistance.employeeasistancemanagement.controllers;

import com.employeeasistance.employeeasistancemanagement.dtos.AssistanceRequest;
import com.employeeasistance.employeeasistancemanagement.dtos.AssistanceResponse;
import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.services.AssistanceService;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AssistanceResponse>> getAllAsistancesByEmployee(@PathVariable UUID employeeId){
        List<Assistance> employeeAssistances = assistanceService.getAllAsistancesByEmployee(employeeId);
        
        
        List<AssistanceResponse> assistancesResponse = employeeAssistances.stream().map(AssistanceResponse::new).toList();
        return ResponseEntity.ok(assistancesResponse);
    }
    
    @PostMapping
    public ResponseEntity<Assistance> createAssistance(@RequestBody AssistanceRequest assistance){
        Assistance assistanceCreated = assistanceService.createAssistance(assistance.getEmployeeId(), assistance.getDate(), assistance.getEntryTime(), assistance.getDepartureTime());
    
        return ResponseEntity.status(HttpStatus.CREATED).body(assistanceCreated);
    }
    
    @PatchMapping("/departure/{id}")
    public ResponseEntity<AssistanceResponse> setDepartureTime(@PathVariable UUID id, @RequestBody LocalTime departureTime){
        Assistance assistanceDepartureUpdated = assistanceService.setDepartureTime(id, departureTime);
    
        return ResponseEntity.ok(new AssistanceResponse(assistanceDepartureUpdated));
    }
}
