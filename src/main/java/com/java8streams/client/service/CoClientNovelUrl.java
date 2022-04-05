package com.java8streams.client.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@PropertySource(value = { "classpath:/clientapi.properties" })
@ConfigurationProperties(prefix = "novelapi.coapi")
public class CoClientNovelUrl {
	private String hostName;
	private String all;
}
