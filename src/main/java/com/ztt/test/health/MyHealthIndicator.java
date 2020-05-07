package com.ztt.test.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * <p>自定义健康端点</p>
 */
@Component("myl")
public class MyHealthIndicator implements HealthIndicator{

	private static final String VERSION = "v1.0.0";
	@Override
	public Health health() {
		int code = check();
		if(code != 0) {
			Health.down().withDetail("version", VERSION).build();
		}
		return Health.up().withDetail("code", code)
				.withDetail("version", VERSION).up().build();
	}
	
	private int check() {
		return 0;
	}

}
