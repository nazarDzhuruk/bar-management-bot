package com.drypalm.easybusiness.service;

import com.drypalm.easybusiness.model.Employee;

public interface EmployeeService extends UtilityService<Employee> {
    Boolean uniqueIdExist(String uniqueId);

    Boolean ifUsernameTaken(String username);

    Employee findEmployeeByUniqueId(String uniqueId);

    Employee findEmployeeByUsername(String username);

    Employee updateRole(long id, String role);

    Employee updateUsername(long id, String username);
}
