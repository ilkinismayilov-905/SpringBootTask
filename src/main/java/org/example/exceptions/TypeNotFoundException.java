package org.example.exceptions;

public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException() {
        System.out.println("There Is No Workspace In This Type");
    }
}
