package com.ztt.test.config;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ztt.test.health.MyEndPoint;

@Configuration
public class MyEndpointConfig {
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnEnabledEndpoint
	public MyEndPoint myEndPoint() {
		return new MyEndPoint();
	}
}
