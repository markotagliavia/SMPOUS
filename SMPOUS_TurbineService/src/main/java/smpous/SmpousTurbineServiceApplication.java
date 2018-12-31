package smpous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class SmpousTurbineServiceApplication 
{
	// turbine stream: http://localhost:8999/turbine/turbine.stream pasete in hystrics dashboard
	// to start straming, push first request on the load-balanced endpoint
	public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(SmpousTurbineServiceApplication.class);    	
    	app.run(args);	
    }
}

