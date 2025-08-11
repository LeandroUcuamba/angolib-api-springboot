package com.leandroucuamba.Angolib.service;

import com.leandroucuamba.Angolib.entity.Autor;
import com.leandroucuamba.Angolib.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leandroucuamba.Angolib.entity.Livro;
import com.leandroucuamba.Angolib.repository.LivroRepository;
import com.leandroucuamba.Angolib.service.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro create(Livro livro) {
        if (livro.getAutor() != null && livro.getAutor().getId() != null) {
            Autor autor = autorRepository.findById(livro.getAutor().getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
            livro.setAutor(autor);
        } else {
            livro.setAutor(null);
        }

        return livroRepository.save(livro);
    }

    public void delete(Long id){
        livroRepository.deleteById(id);
    }

    public Livro getId(Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()){
            throw new EntityNotFound("Livro de ID: "+id+" não encontrado!");
        }
        return livro.get();
    }

    public List<Livro> getAll(){
        return livroRepository.findAll();
    }

    public Livro update(Livro livro){
        Optional<Livro> novoLivro = livroRepository.findById(livro.getId());
        if (novoLivro.isEmpty()){
            throw new EntityNotFound("Livro de ID: "+livro.getId()+" não encontrado!");
        }
        updateLivro(novoLivro, livro);
        return livroRepository.save(novoLivro.get());
    }

    private void updateLivro(Optional<Livro> novoLivro, Livro livro) {
        novoLivro.get().setNome(livro.getNome());
    }
}
