package com.leandroucuamba.Angolib.service.exception;

public class UsuarioNaoExisteException extends RuntimeException{

    public UsuarioNaoExisteException(String mensagem){
        super(mensagem);
    }
}
