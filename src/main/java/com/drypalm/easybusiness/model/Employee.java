package com.drypalm.easybusiness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String role;
    private String uniqueId;
    private boolean status;

    protected Employee() {
    }

    public Employee(String username, String role, String uniqueId, boolean status) {
        this.username = username;
        this.role = role;
        this.uniqueId = uniqueId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(username, employee.username) && Objects.equals(role, employee.role) && Objects.equals(uniqueId, employee.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role, uniqueId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", status=" + status +
                '}';
    }

    public static Employee.EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
        private String username;
        private String role;
        private String uniqueId;
        private boolean status;

        public Employee.EmployeeBuilder username(String username) {
            this.username = username;
            return this;
        }

        public Employee.EmployeeBuilder role(String role) {
            this.role = role;
            return this;
        }

        public Employee.EmployeeBuilder uniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public Employee.EmployeeBuilder status(boolean status) {
            this.status = status;
            return this;
        }

        public Employee build() {
            return new Employee(this.username, this.role, this.uniqueId, this.status);
        }
    }
}
