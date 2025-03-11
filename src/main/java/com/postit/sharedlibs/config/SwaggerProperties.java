package com.postit.sharedlibs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "openapi")
public class SwaggerProperties {
	private String serviceUrl;
	private String title = "API Documentation";
	private String description = "API 문서";
	private String version = "v0.0.1";

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}