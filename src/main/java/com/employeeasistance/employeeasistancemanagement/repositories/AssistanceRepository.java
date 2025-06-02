
package com.employeeasistance.employeeasistancemanagement.repositories;

import com.employeeasistance.employeeasistancemanagement.models.Assistance;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRepository extends JpaRepository<Assistance, UUID>{
    
}
