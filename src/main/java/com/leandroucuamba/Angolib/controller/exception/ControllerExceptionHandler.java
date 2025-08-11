package com.leandroucuamba.Angolib.controller.exception;

import com.leandroucuamba.Angolib.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFound e, HttpServletRequest request){
        String error = "Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<ErrorResponse> usuarioJaExisteError(UsuarioJaExisteException e, HttpServletRequest request){
        String error = "Usuário já está cadastrado no sistema!";
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<ErrorResponse> usuarioNaoExisteError(UsuarioNaoExisteException e, HttpServletRequest request){
        String error = "Usuário não existe no sistema!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<ErrorResponse> LoginInvalidoError(LoginInvalidoException e, HttpServletRequest request){
        String error = "Login Inválido. Tente novamente...";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioNaoTemPermissaoException.class)
    public ResponseEntity<ErrorResponse> UsuarioNaoTemPermissaoError(UsuarioNaoTemPermissaoException e, HttpServletRequest request){
        String error = "Usuário não tem permissão";
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> ErroDeSegurancaDoToken(SecurityException e, HttpServletRequest request){
        String error = "Token foi inválido!";
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
