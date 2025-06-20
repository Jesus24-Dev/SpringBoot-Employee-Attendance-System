
package com.employeeasistance.employeeasistancemanagement;

import com.employeeasistance.employeeasistancemanagement.enums.EmployeePosition;
import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import com.employeeasistance.employeeasistancemanagement.models.User;
import com.employeeasistance.employeeasistancemanagement.repositories.AssistanceRepository;
import com.employeeasistance.employeeasistancemanagement.repositories.EmployeeRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class RepositoryTest {
    
    @Autowired
    private AssistanceRepository assistanceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void shouldReturnTrueIfAssistanceExists(){
        User user = new User();
        entityManager.persist(user);
        
        Employee emp = new Employee(null, "JOHN DOE", "johndoe@mail.com", EmployeePosition.EMPLOYEE, true, user);
        entityManager.persist(emp);
        
        Assistance assis = new Assistance();
        assis.setEmployee(emp);
        assis.setDate(LocalDate.now());
        assis.setEntryTime(LocalTime.now());
        entityManager.persist(assis);
      
        boolean existsAssistanceByDateAndEmployee = assistanceRepository.existsByDateAndEmployee(LocalDate.now(), emp);
        assertTrue(existsAssistanceByDateAndEmployee);
        
        //Trying with unregistered assistance. This should return false
        boolean existsAssistanceByAnotherDateAndEmployee = assistanceRepository.existsByDateAndEmployee(LocalDate.of(2025, Month.MARCH, 28), emp);
        assertFalse(existsAssistanceByAnotherDateAndEmployee);
    }
    
    @Test
    void findAssistanceWithinRange_shouldReturnTheNumberOfAssistancesWithinTheRange(){
        User user = new User();
        entityManager.persist(user);
        
        Employee emp = new Employee(null, "PEDRO GARCIA", "pedrogg@mail.com", EmployeePosition.EMPLOYEE, true, user);
        entityManager.persist(emp);
        
        Assistance a1 = new Assistance(null, emp, LocalDate.of(2025, 6, 1), LocalTime.of(8, 0), LocalTime.of(5,0));
        Assistance a2 = new Assistance(null, emp, LocalDate.of(2025, 6, 2), LocalTime.of(8, 0), LocalTime.of(5,0));
        Assistance a3 = new Assistance(null, emp, LocalDate.of(2025, 6, 3), LocalTime.of(8, 0), LocalTime.of(5,0));
        Assistance outOfRange = new Assistance(null, emp, LocalDate.of(2025, 6, 10), LocalTime.of(8, 0), LocalTime.of(5,0));
    
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);
        entityManager.persist(outOfRange);
        
        List<Assistance> assistances = assistanceRepository.findByDateBetween(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 4));
        
        assertEquals(3, assistances.size());
        
    }
    
    @Test
    void shouldReturnTheNumberOfAssistancesByEmployee(){
        User user = new User();
        entityManager.persist(user);

        Employee emp = new Employee(null, "PEDRO GARCIA", "pedrogg@mail.com", EmployeePosition.EMPLOYEE, true, user);
        entityManager.persist(emp);

        Assistance a1 = new Assistance(null, emp, LocalDate.of(2025, 6, 1), LocalTime.of(8, 0), LocalTime.of(17, 0));
        Assistance a2 = new Assistance(null, emp, LocalDate.of(2025, 6, 2), LocalTime.of(8, 0), LocalTime.of(17, 0));
        Assistance a3 = new Assistance(null, emp, LocalDate.of(2025, 6, 3), LocalTime.of(8, 0), LocalTime.of(17, 0));
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        entityManager.flush();      
        entityManager.refresh(emp);  

        assertNotNull(emp.getEmployeeAssistances());
        assertEquals(3, emp.getEmployeeAssistances().size());
    }
    
    @Test
    void shouldSaveEmployeeAndFoundHimById(){
        User user = new User();
        entityManager.persist(user);
        
        Employee emp = new Employee(null, "JOHN DOE", "johndoe@mail.com", EmployeePosition.EMPLOYEE, true, user);
        entityManager.persist(emp);
        
        Optional<Employee> employeeFounded = employeeRepository.findById(emp.getId());
        
        assertTrue(employeeFounded.isPresent());
    }
}
