package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public enum TypeOfUser implements Serializable{
	registered,
	unregistered,
	admin;
}
