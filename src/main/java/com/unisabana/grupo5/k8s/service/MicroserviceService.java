package com.unisabana.grupo5.k8s.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MicroserviceService {

    public String getAttachedText(){
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("attached.txt")) {
            if (inputStream == null) {
                return "No se encontró el archivo attached.txt en el classpath";
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }
    }
}
