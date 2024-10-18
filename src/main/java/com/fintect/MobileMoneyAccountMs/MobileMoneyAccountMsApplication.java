package com.fintect.MobileMoneyAccountMs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts and customer Managment REST API Documentation",
				description = "Mobile Money microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Samson Oluwaseyi",
						email = "samseyi672@gmail.com",
						url = ""
				),
				license = @License(
						name = "Apache 2.0",
						url = ""
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Mobile Money microservice REST API Documentation",
				url = "l"
		)
)
public class MobileMoneyAccountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileMoneyAccountMsApplication.class, args);
	}

}
