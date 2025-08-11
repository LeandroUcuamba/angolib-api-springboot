package com.leandroucuamba.Angolib.controller;

import com.leandroucuamba.Angolib.dto.TokenDoUsuario;
import com.leandroucuamba.Angolib.dto.UsuarioDeLogin;
import com.leandroucuamba.Angolib.dto.UsuarioDeRegistro;
import com.leandroucuamba.Angolib.entity.Usuario;
import com.leandroucuamba.Angolib.service.JWTService;
import com.leandroucuamba.Angolib.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class UsuarioLoginController {

    private UsuarioService usuarioService;

    private JWTService jwtService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> cadastroUsuario(@RequestBody UsuarioDeRegistro usuarioDeRegistro){
        var usuarioParaSalvar = usuarioService.cadastraUsuario(usuarioDeRegistro);
        return ResponseEntity.ok(usuarioParaSalvar);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDoUsuario> autenticaUsuario(@RequestBody UsuarioDeLogin usuarioDeLogin){
        var tokenDoUsuario = jwtService.autenticaUsuario(usuarioDeLogin);
        return ResponseEntity.ok(tokenDoUsuario);
    }
}
