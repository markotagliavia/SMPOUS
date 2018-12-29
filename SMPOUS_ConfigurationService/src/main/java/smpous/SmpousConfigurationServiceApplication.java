package smpous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableAutoConfiguration
public class SmpousConfigurationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmpousConfigurationServiceApplication.class, args);
	}

}

