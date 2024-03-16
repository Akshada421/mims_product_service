package com.mims.product;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
		String HOST_NAME = "";
		try {
			HOST_NAME = getHostname();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new OpenAPI()
				.info(new Info().title("mims_product_service").description("APIs for MIMS Product Service")
						.version("1.0").license(new License().name("MIMS Dev Team").url("http://localhost:9091/")))
				.externalDocs(new ExternalDocumentation().description("MIMS Product Service Documentation")
						.url(("http://").concat(HOST_NAME).concat(":").concat(server_port).concat(context_path)));
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		final String HOST_NAME;
		final String HTTP_STRING = "http://";
		try {
			HOST_NAME = getHostname();
			return (args) -> logger.info("URL of your API is : {}",
					HTTP_STRING.concat(HOST_NAME).concat(":").concat(server_port).concat(context_path));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String getHostname() throws UnknownHostException {
		String hostname = "";
		String localHost_hostAddress = InetAddress.getLocalHost().getHostAddress();	
		String loopback_hostname = InetAddress.getLoopbackAddress().getHostName();
		logger.info("HostAddress :{}", localHost_hostAddress);	
		logger.info("hostname :{}", loopback_hostname);
		if (localHost_hostAddress != null) {
			logger.info("---Setting up the host---");
			hostname = localHost_hostAddress;
		} else {
			logger.info("---Setting up the default host---");
			hostname = "localhost:";
		}
		return hostname;
	}

}
