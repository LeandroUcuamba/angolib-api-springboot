package com.leandroucuamba.Angolib.service.exception;

public class UsuarioJaExisteException extends RuntimeException{

    public UsuarioJaExisteException(String mensagem){
        super(mensagem);
    }
}
