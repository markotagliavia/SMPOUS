package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public enum TheaterType implements Serializable{
	normal(1),
	projection3D(2),
	projection4D(3);
	private final int value;
	private TheaterType(int value) {
        this.value = value;
    }
}
