package carefirst.employee_management.service;

import java.util.List;

import carefirst.employee_management.entity.Employee;

public interface EmployeeService {
    Employee getEmployee(Long employeeId);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long employeeId, Employee employee);
    void deleteEmployee(Long employeeId);
    List<Employee> getEmployees();
}
