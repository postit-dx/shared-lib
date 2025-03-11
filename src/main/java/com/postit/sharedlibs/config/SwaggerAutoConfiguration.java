package com.postit.sharedlibs.config;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnClass(OpenAPI.class)
@AutoConfiguration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {
	@Bean
	@ConditionalOnMissingBean
	public OpenAPI customOpenApi(SwaggerProperties properties) {
		log.info("serverUrl={}", properties.getServiceUrl());

		return new OpenAPI()
			.servers(List.of(new Server().url(properties.getServiceUrl())))
			// .components(new Components().addSecuritySchemes("Bearer",
			// 	new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
			// .addSecurityItem(new SecurityRequirement().addList("Bearer"))
			.info(new Info().title(properties.getTitle())
				.description(properties.getDescription())
				.version(properties.getVersion()));
	}

	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
}
