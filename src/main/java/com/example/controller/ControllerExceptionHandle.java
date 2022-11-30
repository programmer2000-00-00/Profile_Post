package com.example.controller;

import com.example.exp.CreateException;
import com.example.exp.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler({ItemNotFoundException.class, CreateException.class})

    public ResponseEntity<?>handle(RuntimeException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
}
