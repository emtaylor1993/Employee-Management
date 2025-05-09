package carefirst.employee_management.repository;

import org.springframework.data.repository.CrudRepository;
import carefirst.employee_management.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}