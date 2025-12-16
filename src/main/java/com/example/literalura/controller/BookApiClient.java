package com.example.literalura.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class BookApiClient {

    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    public String buscarLivroPorTitulo(String titulo) {
        String url = BASE_URL + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        return new RestTemplate().getForObject(url, String.class);
    }
}
