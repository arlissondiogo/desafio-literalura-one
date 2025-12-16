package com.example.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonConversor implements IJsonConversor {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T dateConversor(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON", e);
        }
    }
}
