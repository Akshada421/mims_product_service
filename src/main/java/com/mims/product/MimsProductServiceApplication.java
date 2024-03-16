package com.mims.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class MimsProductServiceApplication {
	
	Logger logger = LoggerFactory.getLogger(MimsProductServiceApplication.class);

	@Value("${server.servlet.context-path}")
	String context_path;
	
	@Value("${server.port}")
	String server_port;

	public static void main(String[] args) {
		SpringApplication.run(MimsProductServiceApplication.class, args);
	}

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("mims_product_service").description("APIs for MIMS Product Service")
						.version("1.0").license(new License().name("MIMS Dev Team").url("http://localhost:9091/")))
				.externalDocs(new ExternalDocumentation().description("MIMS Product Service Documentation")
						.url("http://localhost:9091/"));
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {		
		final String BASE_LOCALHOST_URL = "http://localhost:";
		return (args) -> logger.info("URL of your API is : {}",
				BASE_LOCALHOST_URL.concat(server_port).concat(context_path));
	}

}
