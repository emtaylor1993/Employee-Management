package carefirst.employee_management.controller;

import java.time.LocalDate;
import java.util.List;

import carefirst.employee_management.constants.CodeConstants;
import carefirst.employee_management.exceptions.InvalidAgeException;
import carefirst.employee_management.utilities.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carefirst.employee_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import carefirst.employee_management.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Management API", description = "Requests related to employee management")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Get all employees", description = "Returns a list of all employees")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employees Found",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an employee by ID", description = "Returns an employee by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee Found",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "404", description = "Employee Not Found",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public Employee getEmployee(
        @Parameter(description = "ID of the employee", required = true)
        @PathVariable(name = "id") Long employeeId
    ) {
        return employeeService.getEmployee(employeeId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Employee Deleted",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "404", description = "Employee Not Found",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Void> deleteEmployee(
        @Parameter(description = "ID of the employee", required = true)
        @PathVariable(name = "id") Long employeeId
    ) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Creates a new employee", description = "Adds a new employee to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Employee Created",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public Employee createEmployee(@RequestBody Employee employee) {
        int age = CalculateAge.calculateAge(employee.getBirthDate(), LocalDate.now());

        if (age < 18 || age > 64) {
            throw new InvalidAgeException(CodeConstants.INVALID_AGE);
        }

        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Updates an employee's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee Updated",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "404", description = "Employee Not Found",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public Employee updateEmployee(
        @Parameter(description = "ID of the employee", required = true)
        @PathVariable(name = "id") Long employeeId, @RequestBody Employee employee
    ) {
        int age = CalculateAge.calculateAge(employee.getBirthDate(), LocalDate.now());

        if (age < 18 || age > 64) {
            throw new InvalidAgeException(CodeConstants.INVALID_AGE);
        }

        return employeeService.updateEmployee(employeeId, employee);
    }
}
