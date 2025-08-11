package com.leandroucuamba.Angolib.controller;

import com.leandroucuamba.Angolib.service.UsuarioService;
import com.leandroucuamba.Angolib.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/usuarios")
public class UsuarioController {

    private UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable String email, @RequestHeader("Authorization") String header){
        var usuarioRetornado = service.buscaUsuario(email, header);
        return ResponseEntity.ok(usuarioRetornado);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> removeUsuario(@PathVariable String email, @RequestHeader("Authorization") String header){
        service.removerUsuario(email, header);
        return ResponseEntity.ok().build();
    }
}
