package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.Employee;
import com.drypalm.easybusiness.repository.EmployeeRepository;
import com.drypalm.easybusiness.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class EmployeeAction implements EmployeeService {
    private static final String CLASS_NAME = "EmployeeService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "employee not found" + TEXT_RESET;

    private final EmployeeRepository repository;

    public EmployeeAction(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee add(Employee entity) {
        return repository.save(entity);
    }

    @Override
    public Employee getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
    }

    @Override
    public Employee removeById(long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        repository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> index() {
        return repository.findAll();
    }

    @Override
    public Boolean uniqueIdExist(String uniqueId) {
        return repository.ifUniqueIdExist(uniqueId);
    }

    @Override
    public Boolean ifUsernameTaken(String username) {
        return repository.ifUsernameTaken(username);
    }

    @Override
    public Employee findEmployeeByUniqueId(String uniqueId) {
        return repository.findEmployeeByUniqueId(uniqueId);
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return repository.findEmployeeByUsername(username);
    }

    @Override
    public Employee updateRole(long id, String role) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        employee.setRole(role);
        repository.save(employee);
        return employee;
    }

    @Override
    public Employee updateUsername(long id, String username) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        employee.setUsername(username);
        repository.save(employee);
        return employee;
    }
}
