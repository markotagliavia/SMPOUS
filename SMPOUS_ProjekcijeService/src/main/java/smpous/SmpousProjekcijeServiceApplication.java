package smpous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class SmpousProjekcijeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmpousProjekcijeServiceApplication.class, args);
	}

}

