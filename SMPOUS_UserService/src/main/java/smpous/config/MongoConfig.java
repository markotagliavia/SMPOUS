package smpous.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

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
	    List<ServerAddress> saList = new ArrayList<>();
	    saList.add(new ServerAddress("smpous-shard-00-00-tq9zq.mongodb.net", 27017));
	    //saList.add(new ServerAddress("cluster0-shard-00-01-75shm.gcp.mongodb.net", 27017));
	    //saList.add(new ServerAddress("cluster0-shard-00-02-75shm.gcp.mongodb.net", 27017));

	    char[] pwd =  "admin".toCharArray();
	    MongoCredential credential = MongoCredential.createCredential("admin", "admin", pwd);

	    //set sslEnabled to true here
	    MongoClientOptions options = MongoClientOptions.builder()
	            .readPreference(ReadPreference.primaryPreferred())
	            .retryWrites(true)
	            .maxConnectionIdleTime(6000)
	            .sslEnabled(true)
	            .build();

	    MongoClient mongoClient = new MongoClient(saList, credential, options);     
	    return mongoClient;
	}
}
