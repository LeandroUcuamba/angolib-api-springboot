package com.leandroucuamba.Angolib.service;

import com.leandroucuamba.Angolib.dto.UsuarioDeRegistro;
import com.leandroucuamba.Angolib.entity.Usuario;
import com.leandroucuamba.Angolib.repository.UsuarioRepository;
import com.leandroucuamba.Angolib.service.exception.UsuarioJaExisteException;
import com.leandroucuamba.Angolib.service.exception.UsuarioNaoExisteException;
import com.leandroucuamba.Angolib.service.exception.UsuarioNaoTemPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    private JWTService service;

    @Autowired
    public UsuarioService(UsuarioRepository repository, JWTService service){
        this.repository = repository;
        this.service = service;
    }

    public Usuario cadastraUsuario(UsuarioDeRegistro usuarioDeRegistro){
        Optional<Usuario> usuarioRetornado = repository.findByEmail(usuarioDeRegistro.email());

        if(usuarioRetornado.isPresent()){
            throw new UsuarioJaExisteException("Usuário já existe no sistema!");
        }

        Usuario usuarioParaSalvar = new Usuario(usuarioDeRegistro);
        repository.save(usuarioParaSalvar);

        return usuarioParaSalvar;
    }

    private boolean usuarioTemPermissao(String header, String email){
        String sujeitoDoToken = service.pegaSujeitoPeloToken(header);
        Optional<Usuario> usuarioRetornado = repository.findByEmail(sujeitoDoToken);
        return usuarioRetornado.isPresent() && usuarioRetornado.get().getEmail().equals(email);
    }

    public Usuario buscaUsuario(String email, String header){
        Optional<Usuario> usuarioRetornado = repository.findByEmail(email);

        if(usuarioRetornado.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não existe!");
        }

        if(!usuarioTemPermissao(header, email)){
            throw new UsuarioNaoTemPermissao("O usuário não tem permissão para acessar este recurso!");
        }

        return usuarioRetornado.get();
    }

    public void removerUsuario(String email, String header){
        Optional<Usuario> usuarioParaRemover = repository.findByEmail(email);

        if(usuarioParaRemover.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não existe!");
        }

        if(!usuarioTemPermissao(header, email)){
            throw new UsuarioNaoTemPermissao("O usuário não tem permissão para acessar este recurso!");
        }

        repository.delete(usuarioParaRemover.get());
    }
}
