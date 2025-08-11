package com.leandroucuamba.Angolib.service.exception;

public class LoginInvalidoException extends RuntimeException{

    public LoginInvalidoException(String mensagem){
        super(mensagem);
    }
}
