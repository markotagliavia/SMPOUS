package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public enum Genre implements Serializable {
	   action,
	   adventure,
	   kids,
	   animation,
	   comedy,
	   crimi,
	   documenatary,
	   drama,
	   sciFi,
	   horror,
	   musical,
	   mistery,
	   romance,
	   triler,
	   western;
}
