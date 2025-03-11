package org.example.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException() {

        System.out.println("There is no customer with given ID");
    }
}
