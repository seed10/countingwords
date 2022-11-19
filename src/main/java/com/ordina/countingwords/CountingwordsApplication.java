package com.ordina.countingwords;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "WordCounter API", version = "1.0"))
public class CountingwordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountingwordsApplication.class, args);
    }

}
