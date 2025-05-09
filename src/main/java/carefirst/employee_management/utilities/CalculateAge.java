package carefirst.employee_management.utilities;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {
    public static int calculateAge(LocalDate birthDate, LocalDate now) {
        return Period.between(birthDate, now).getYears();
    }
}
