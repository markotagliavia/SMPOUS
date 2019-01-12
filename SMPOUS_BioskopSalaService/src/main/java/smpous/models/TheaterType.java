package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public enum TheaterType implements Serializable{
	normal("normal"),
	projection3D("projection3D"),
	projection4D("projection4D");
	private final String value;
	private TheaterType(String value) {
        this.value = value;
    }
}
