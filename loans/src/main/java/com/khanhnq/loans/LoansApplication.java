package com.khanhnq.loans;

import com.khanhnq.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Nguyen Quoc Khanh",
						email = "khanhnq@gmail.com",
						url = "https://www.khanhnq.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.khanhnq.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Loans microservice REST API Documentation",
				url = "https://www.khanhnq.com"
		)
)
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
