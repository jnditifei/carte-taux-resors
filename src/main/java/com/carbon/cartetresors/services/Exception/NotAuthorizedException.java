package com.carbon.cartetresors.services.Exception;

public class NotAuthorizedException extends Exception{
    private String message;

    public NotAuthorizedException(String message) {
        super(message);
    }
}
