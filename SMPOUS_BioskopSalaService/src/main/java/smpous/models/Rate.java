package smpous.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	
    private static class Cache {
        private static Map<Integer,Rate> CACHE = new HashMap<Integer,Rate>();

        static {
            for (final Rate d : Rate.values()) {
                CACHE.put(d.value, d);
            }
        }
    }

    public static Rate getRate(int i) {
        return Cache.CACHE.get(i);
    }
}