package org.example.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public T mapObject(String value, Class<T> tClass) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T res = objectMapper.readValue(value, tClass);
            return res;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
