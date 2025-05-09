package carefirst.employee_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import carefirst.employee_management.entity.Employee;
import carefirst.employee_management.exceptions.EmployeeNotFoundException;
import carefirst.employee_management.repository.EmployeeRepository;
import carefirst.employee_management.service.EmployeeServiceImpl;

class EmployeeManagementApplicationTests {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	private Employee mockEmployee;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockEmployee = Employee.builder()
			.employeeId(6L)
			.firstName("Emmanuel")
			.lastName("Taylor")
			.emailAddress("emmanuel.taylor@carefirst.com")
			.phone("202-555-0111")
			.birthDate(LocalDate.of(1993, 5, 11))
			.jobTitle("Software Engineer")
			.department("IT")
			.location("Baltimore")
			.startDate(LocalDate.of(2025, 5, 7))
			.manager("John Doe")
			.build();
	}

	@Test
	void testGetEmployee() {
		when(employeeRepository.findById(6L)).thenReturn(Optional.of(mockEmployee));
		Employee result = employeeService.getEmployee(6L);
		assertEquals("Emmanuel", result.getFirstName());
		assertEquals("emmanuel.taylor@carefirst.com", result.getEmailAddress());
	}

	@Test
	void testGetEmployeeInvalidId() {
		when(employeeRepository.findById(99999L)).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(99999L));
	}

	@Test
	void testCreateEmployee() {
		when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);
		Employee employee = employeeService.createEmployee(mockEmployee);
		assertEquals("Emmanuel", employee.getFirstName());
		assertEquals("emmanuel.taylor@carefirst.com", employee.getEmailAddress());
	}

	@Test
	void testUpdateEmployee() {
		Employee employeeUpdate = mockEmployee.toBuilder().jobTitle("Senior Software Engineer").build();
		when(employeeRepository.findById(6L)).thenReturn(Optional.of(mockEmployee));
		when(employeeRepository.save(mockEmployee)).thenReturn(employeeUpdate);
		Employee employee = employeeService.updateEmployee(6L, employeeUpdate);
		assertEquals("Senior Software Engineer", employee.getJobTitle());
	}

	@Test
	void testDeleteEmployee() {
		when(employeeRepository.findById(6L)).thenReturn(Optional.of(mockEmployee));
		employeeService.deleteEmployee(6L);
		verify(employeeRepository).delete(mockEmployee);
	}

	@Test
	void testDeleteEmployeeInvalidId() {
		when(employeeRepository.findById(123L)).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(123L));
	}

	@Test
	void testGetEmployees() {
		when(employeeRepository.findAll()).thenReturn(java.util.List.of(mockEmployee));
		assertEquals(1, employeeService.getEmployees().size());
	}
}
