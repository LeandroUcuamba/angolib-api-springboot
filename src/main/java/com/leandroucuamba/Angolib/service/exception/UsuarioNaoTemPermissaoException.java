package com.leandroucuamba.Angolib.service.exception;

public class UsuarioNaoTemPermissaoException extends RuntimeException{

    public UsuarioNaoTemPermissaoException(String mensagem){
        super(mensagem);
    }
}
