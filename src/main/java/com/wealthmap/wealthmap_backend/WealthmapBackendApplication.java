package com.wealthmap.wealthmap_backend;

import com.wealthmap.wealthmap_backend.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wealthmap.wealthmap_backend")
public class WealthmapBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WealthmapBackendApplication.class);
		app.addInitializers(new EnvLoader()); // <- This line is important
		app.run(args);
	}

}



