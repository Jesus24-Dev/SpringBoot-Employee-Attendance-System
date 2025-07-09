
package com.employeeasistance.employeeasistancemanagement;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.repositories.AssistanceRepository;
import com.employeeasistance.employeeasistancemanagement.repositories.EmployeeRepository;
import com.employeeasistance.employeeasistancemanagement.services.AssistanceService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    
    @Mock
    private AssistanceRepository assistanceRepository;
    
    @Mock
    private EmployeeRepository employeeRepository;
    
    @InjectMocks
    private AssistanceService assistanceService;
    
    @Test
    void shouldRegisterIfEmployeeExistsAndThereIsNoRegistration(){
        Employee emp = new Employee(null,
                "STEFFANY GIL",
                "sttffgil@example.com",
                EmployeePosition.EMPLOYEE,
                true,
                new User());
        
        when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        when(assistanceRepository.existsByDateAndEmployee(LocalDate.now(), emp)).thenReturn(false);
        
        assertNotNull(assistanceService.createAssistance(emp.getId(), LocalDate.now(), LocalTime.now(), null));
    }
    
    @Test
    void shouldNotRegisterIfEmployeeDoesNotExists(){
        UUID id = UUID.randomUUID();
        
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
        assistanceService.createAssistance(id, LocalDate.now(), LocalTime.now(), null);
    });
       
        assertEquals(ex.getMessage(), "Employee not found: " + id);
    }   
    
    @Test
    void shouldNotRegisterIfAssistanceAlreadyExists(){
        Employee emp = new Employee(null, "STEFFANY GIL", "sttffgil@example.com", EmployeePosition.EMPLOYEE, true, new User());
        
        when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        when(assistanceRepository.existsByDateAndEmployee(LocalDate.now(), emp)).thenReturn(true);
        
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            assistanceService.createAssistance(emp.getId(), LocalDate.now(), LocalTime.now(), null);
        });
        
        assertEquals(ex.getMessage(), "There is a register with for this employee today");
    }
    
    @Test
    void shouldReturnAllAssistancesByEmployee() {
        UUID employeeId = UUID.randomUUID();
        Employee emp = new Employee(employeeId, "JULIAN RAMIREZ", "jjmirez@mail.com", EmployeePosition.EMPLOYEE, true, new User());

        List<Assistance> asistenciasMock = List.of(
            new Assistance(null, emp, LocalDate.of(2025, 6, 1), LocalTime.of(8, 0), LocalTime.of(17, 0)),
                new Assistance(null, emp, LocalDate.of(2025, 6, 2), LocalTime.of(8, 0), LocalTime.of(17, 0))
        );

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp));
        when(assistanceRepository.findAllByEmployee(emp)).thenReturn(asistenciasMock);

        List<Assistance> resultado = assistanceService.getAllAssistancesByEmployee(employeeId);

        assertEquals(2, resultado.size());
        assertEquals(asistenciasMock, resultado);
    }
}
