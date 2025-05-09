package carefirst.employee_management.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "employee")
@Schema(description = "Employee entity representing staff details")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Auto-generated employee ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long employeeId;

    @NonNull
    @Column(name = "first_name", nullable = false)
    @Schema(description = "Employee's first name", example = "Alice")
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    @Schema(description = "Employee's last name", example = "Johnson")
    private String lastName;

    @NonNull
    @Column(name = "email_address", nullable = false, unique = true)
    @Schema(description = "Employee's unique email address", example = "alice.johnson@carefirst.com")
    private String emailAddress;

    @NonNull
    @Column(name = "phone", nullable = false)
    @Schema(description = "Employee's phone number", example = "202-555-0111")
    private String phone;

    @NonNull
    @Column(name = "birth_date", nullable = false)
    @Schema(description = "Employee's birth date", example = "1985-03-12")
    private LocalDate birthDate;

    @NonNull
    @Column(name = "job_title", nullable = false)
    @Schema(description = "Employee's job title", example = "Software Engineer")
    private String jobTitle;

    @NonNull
    @Column(name = "department", nullable = false)
    @Schema(description = "Employee's job department", example = "IT")
    private String department;

    @NonNull
    @Column(name = "location", nullable = false)
    @Schema(description = "Employee's office location", example = "Baltimore")
    private String location;

    @NonNull
    @Column(name = "start_date", nullable = false)
    @Schema(description = "Employee's start date", example = "2020-01-05")
    private LocalDate startDate;

    @NonNull
    @Column(name = "manager_reporting", nullable = false)
    @Schema(description = "Employee's reporting manager", example = "Bob Smith")
    private String manager;
}
