package org.example.exceptionHandler;

import org.example.exceptions.NotFoundByIdException;
import org.example.exceptions.RequiredNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NotFoundByIdException.class)
//    public static ResponseEntity<String> handleNotFoundException(NotFoundByIdException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RequiredNameException.class)
//    public ResponseEntity<String > handleRequiredNameException(RequiredNameException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//}
