package com.mims.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class MimsProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimsProductServiceApplication.class, args);
	}

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("mims_product_service").description("APIs for MIMS Product Service").version("1.0")
						.license(new License().name("Dev Team").url("http://localhost:9091/")))
				.externalDocs(new ExternalDocumentation().description("MIMS Product Service Documentation").url("http://localhost:9091/"));
	}

}
