package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{


    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        while ((cause != null) && !(cause instanceof org.hibernate.exception.ConstraintViolationException || cause instanceof MethodArgumentNotValidException)) { //if no hibernate or jackson exception and there is still a cause then keep looping
            cause = cause.getCause(); //gets the next cause(exception) if the next one is null then that means that there is no other exceptions
        }

        List<ValidationError> listVE = new ArrayList<>();
        if(cause != null) {
            // dealing with hibernate exceptions
            if(cause instanceof org.hibernate.exception.ConstraintViolationException) {
                org.hibernate.exception.ConstraintViolationException ex = (ConstraintViolationException) cause;
                ValidationError newVe = new ValidationError();
                newVe.setCode(ex.getMessage());
                newVe.setMessage(ex.getConstraintName());
                listVE.add(newVe);
            //dealing with jackson exceptions
            } else {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for(FieldError err : fieldErrors) {
                    ValidationError newVe = new ValidationError();
                    newVe.setCode(err.getField());
                    newVe.setMessage(err.getDefaultMessage());
                    listVE.add(newVe);
                }
            }
        }

        return listVE;
    }
}
