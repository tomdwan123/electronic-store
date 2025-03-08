package com.be.electronic_store.exception;

import java.util.Map;

public interface PropertiesProducer {

    Map<String, Object> getProperties();

    void setProperties(Map<String, Object> properties);
}
