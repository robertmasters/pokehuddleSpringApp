package com.pokehuddle.pokehuddlebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "file:/Users/rober/pokehuddlerest.properties", ignoreResourceNotFound = true) // if cannot find file ignore
public class PokehuddleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokehuddleBackendApplication.class, args);
	}

}
