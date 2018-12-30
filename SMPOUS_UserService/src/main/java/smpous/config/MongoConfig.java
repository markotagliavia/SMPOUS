package smpous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${mongodb.host}")
	private String mongodb_host; 

	@Value("${mongodb.port}")
	private int mongodb_port; 

	@Value("${mongodb.databasename}")
	private String mongodb_databasename;
	
	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Override
	public String getDatabaseName() {
		return mongodb_databasename;
	}

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient(mongodb_host, mongodb_port);
	}
}
