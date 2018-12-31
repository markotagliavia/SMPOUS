package smpous.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
class RibbonConfig{
    @Bean
    public IRule ribbonRule() {
    	
    	/*A loadbalacing strategy that randomly distributes traffic amongst existing servers.*/
       // return new RandomRule();
        
    	/*This rule simply choose servers by round robin. It is often used as the default rule or fallback of more advanced rules.*/
        return new RoundRobinRule();
        
        /*This rule will skip servers that are deemed "circuit tripped" or with high concurrent connection count.
         *By default, an instance is circuit tripped if the RestClient fails to make a connection to it for the last three times.
         *Once an instance is circuit tripped, it will remain in this state for 30 seconds before the circuit is deemed as closed again. 
         *However, if it continues to fail connections, it will become "circuit tripped" again and the wait time for it to become "circuit closed" will increase exponentially to the number of consecutive failures.
         */
        //return new AvailabilityFilteringRule()
        
        /*For this rule, each server is given a weight according to its average response time. 
         *The longer the response time, the less weight it will get. 
         *The rule randomly picks a server where the possibility is determined by server's weight.
         */
    	//return new WeightedResponseTimeRule();
        
        /*Server zones based roles*/
        
        //A rule that skips servers with "tripped" circuit breaker and picks the server with lowest concurrent requests.
    	//return new BestAvailableRule();
        
        //Avoids the Amazon EC2 zone that has the most malfunctioning servers
    	//return new ZoneAvoidanceRule();
    }
}

@Configuration
@RibbonClient(name = "user-service", configuration = RibbonConfig.class)
class TestConfiguration {
}
