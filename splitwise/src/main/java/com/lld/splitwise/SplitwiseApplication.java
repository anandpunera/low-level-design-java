package com.lld.splitwise;


import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitwiseApplication {

    private static final String EMPTY_STRING_DELIMITER = " ";

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }
}
