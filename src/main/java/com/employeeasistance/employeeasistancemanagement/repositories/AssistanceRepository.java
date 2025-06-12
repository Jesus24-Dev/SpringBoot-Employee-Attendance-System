
package com.employeeasistance.employeeasistancemanagement.repositories;

import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import com.employeeasistance.employeeasistancemanagement.models.Employee;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssistanceRepository extends JpaRepository<Assistance, UUID>{
    List<Assistance> findByDate(LocalDate date);
    boolean existsByDateAndEmployee(LocalDate date, Employee employee);
    
    @Query("SELECT a from assistance WHERE a.date BETWEEN :startDate AND :finishDate")
    List<Assistance> findAssistanceBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("finishDate") LocalDate finishDate);
}
