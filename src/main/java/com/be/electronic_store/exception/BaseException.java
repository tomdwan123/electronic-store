package com.be.electronic_store.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BaseException extends RuntimeException implements PropertiesProducer {

    private Map<String, Object> properties = new HashMap<>();

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Map<String, Object> properties) {
        super(message);
        this.properties = properties;
    }
}
