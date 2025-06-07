
package com.employeeasistance.employeeasistancemanagement.services;
import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.repositories.AssistanceRepository;
import com.employeeasistance.employeeasistancemanagement.repositories.EmployeeRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceService {
    private final AssistanceRepository assistanceRepository;
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public AssistanceService(AssistanceRepository assistanceRepository, EmployeeRepository employeeRepository){
        this.assistanceRepository = assistanceRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public List<Assistance> getAllAssistances(){
        return assistanceRepository.findAll();
    }
    
    public List<Assistance> getAllAssistancesByEmployee(UUID employeeId){
        Employee employeeFounded = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));
        
        return employeeFounded.getEmployeeAssistances();
    }
    
    public List<Assistance> getAllAssistancesByDate(LocalDate date){
        List<Assistance> assistancesFounded = assistanceRepository.findByDate(date);
        return assistancesFounded;
    }
    
    public Assistance createAssistance(UUID employeeId, LocalDate date, LocalTime entryTime, LocalTime departureTime){
        Employee employeeFounded = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));
        
        Assistance newAssistance = new Assistance();
        
        newAssistance.setEmployee(employeeFounded);
        newAssistance.setDate(date);
        newAssistance.setEntryTime(entryTime);
        newAssistance.setDepartureTime(departureTime);    
        
        assistanceRepository.save(newAssistance);       
        return newAssistance;
    }
    
    public Assistance updateAssistance(UUID id, LocalDate date, LocalTime entryTime, LocalTime departureTime){
        Assistance assistanceFounded = assistanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        assistanceFounded.setDate(date);
        assistanceFounded.setEntryTime(entryTime);
        assistanceFounded.setDepartureTime(departureTime);
        
        assistanceRepository.save(assistanceFounded);
        
        return assistanceFounded;
    }
    
    public Assistance setDepartureTime(UUID id, LocalTime departureTime){
        Assistance assistanceFounded = assistanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        assistanceFounded.setDepartureTime(departureTime);
        assistanceRepository.save(assistanceFounded);
        
        return assistanceFounded;
    }
    
    public void deleteAssistance(UUID id){
        Assistance assistanceFounded = assistanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        
        assistanceRepository.delete(assistanceFounded);
    }
}
