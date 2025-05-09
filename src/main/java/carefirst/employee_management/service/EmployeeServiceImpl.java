package carefirst.employee_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carefirst.employee_management.entity.Employee;
import carefirst.employee_management.exceptions.EmployeeNotFoundException;
import carefirst.employee_management.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return unwrapEmployee(employee, employeeId);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employeeUpdates) {
        Employee employee = getEmployee(employeeId);
        BeanUtils.copyProperties(employeeUpdates, employee, "employeeId");
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    static Employee unwrapEmployee(Optional<Employee> employee, Long employeeId) {
        if (employee.isPresent()) return employee.get();
        else throw new EmployeeNotFoundException(employeeId);
    }
}
