package com.raj.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found on sever !!!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }


}
