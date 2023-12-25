package org.example.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FindMapper<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public T mapObject(String value, Class<T> tClass) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String result = value.substring(value.indexOf("trackmatches") + 14);
            T res = objectMapper.readValue(result, tClass);
            return res;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

