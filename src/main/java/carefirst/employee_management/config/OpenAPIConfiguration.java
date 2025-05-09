package carefirst.employee_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
    
    @Bean
    public OpenAPI employeeManagementOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("CareFirst Employee Management API")
                .version("1.0.0")
                .description("REST API for CareFirst Employee Management")
                .contact(new Contact()
                    .name("Emmanuel Taylor")
                    .email("emtaylor1993@gmail.com")));
    }
}
