package com.pokehuddle.pokehuddlebackend.exceptions;

public class ResourceNotFoundException extends RuntimeException { //extend runtimeException makes this a throwable exception
    public ResourceNotFoundException(String message) {
        super("Error from pokehuddle application " + message);
    }
}
