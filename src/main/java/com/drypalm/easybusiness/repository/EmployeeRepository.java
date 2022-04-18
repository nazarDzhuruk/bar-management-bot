package com.drypalm.easybusiness.repository;

import com.drypalm.easybusiness.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT CASE WHEN COUNT (e) > 0 THEN TRUE ELSE FALSE END FROM Employee e WHERE e.uniqueId = ?1")
    Boolean ifUniqueIdExist(String uniqueId);

    @Query("SELECT CASE WHEN COUNT (e) > 0 THEN TRUE ELSE FALSE END FROM Employee e WHERE e.username = ?1")
    Boolean ifUsernameTaken(String username);

    Employee findEmployeeByUniqueId(String uniqueId);

    Employee findEmployeeByUsername(String username);
}
