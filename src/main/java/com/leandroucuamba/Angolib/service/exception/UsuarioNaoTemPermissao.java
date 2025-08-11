package com.leandroucuamba.Angolib.service.exception;

public class UsuarioNaoTemPermissao extends RuntimeException{

    public UsuarioNaoTemPermissao(String mensagem){
        super(mensagem);
    }
}
