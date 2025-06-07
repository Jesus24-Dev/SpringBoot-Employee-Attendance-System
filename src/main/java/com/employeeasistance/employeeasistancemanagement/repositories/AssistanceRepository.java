
package com.employeeasistance.employeeasistancemanagement.repositories;

import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRepository extends JpaRepository<Assistance, UUID>{
    List<Assistance> findByDate(LocalDate date);
}
