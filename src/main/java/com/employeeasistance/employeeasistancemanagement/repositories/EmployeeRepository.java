
package com.employeeasistance.employeeasistancemanagement.repositories;

import com.employeeasistance.employeeasistancemanagement.models.Employee;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{
    
}
