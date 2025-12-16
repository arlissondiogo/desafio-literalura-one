package com.example.literalura.service;

public interface IJsonConversor {
    <T> T dateConversor(String json, Class<T> classe);
}
