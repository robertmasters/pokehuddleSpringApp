package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}
