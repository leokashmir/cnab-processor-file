package com.cnab.processor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StorageFileConfig {

    @Value("${cnab.files.upload.dir}")
    private String uploadStorage;

    @Bean
    public Path getDirFileUpload() {

        Path dir = Paths.get(uploadStorage).toAbsolutePath().normalize();
        try {
            if (!Files.exists(dir)) { Files.createDirectories(dir); }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dir;
    }

}
