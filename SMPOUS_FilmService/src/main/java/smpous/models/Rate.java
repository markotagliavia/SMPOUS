package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public enum Rate implements Serializable{
	one(1),
	two(2),
	three(3),
	four(4),
	five(5);
	
	private final int value;
	private Rate(int value) {
        this.value = value;
    }
}
