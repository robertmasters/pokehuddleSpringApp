package com.pokehuddle.pokehuddlebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PokehuddleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokehuddleBackendApplication.class, args);
	}

}
