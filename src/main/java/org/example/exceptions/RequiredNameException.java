package org.example.exceptions;

public class RequiredNameException extends RuntimeException
{
  public RequiredNameException() {

    System.out.println("Name cannot be null");;
  }
}
