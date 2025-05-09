package carefirst.employee_management.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long employeeId) {
        super("The Employee ID: '" + employeeId + "' does not exist in our records.");
    }
}
