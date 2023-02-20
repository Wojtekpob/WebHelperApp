package com.study.StudyHelperApp.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String msg){
        super(msg);
    }
}
