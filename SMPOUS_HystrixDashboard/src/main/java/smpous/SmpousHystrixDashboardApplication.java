package smpous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class SmpousHystrixDashboardApplication {
	
	//monitor on http://localhost:7979/hystrix
	//url string  http://localhost:9000/hystrix.stream - for individual streams
	//		      http://localhost:8999/turbine.stream - for all streams using turbine

	public static void main(String[] args) {
		SpringApplication.run(SmpousHystrixDashboardApplication.class, args);
	}
}

