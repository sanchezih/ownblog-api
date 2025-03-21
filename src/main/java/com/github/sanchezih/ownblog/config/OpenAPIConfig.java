package com.github.sanchezih.ownblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("OwnBlog API").version("1.0.0")
				.description("API REST para gestionar el blog de OwnBlog")
				.contact(new Contact().name("Juan Perez").email("jperez@example.com")
						.url("https://github.com/sanchezih/ownblog-api"))
				.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")));
	}
}