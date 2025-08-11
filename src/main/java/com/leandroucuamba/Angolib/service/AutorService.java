package com.leandroucuamba.Angolib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leandroucuamba.Angolib.entity.Autor;
import com.leandroucuamba.Angolib.repository.AutorRepository;
import com.leandroucuamba.Angolib.service.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    public Autor create(Autor autor){
        return repository.save(autor);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Autor getId(Long id){
        Optional<Autor> autor = repository.findById(id);
        if (autor.isEmpty()){
            throw new EntityNotFound("Autor de ID: "+id+" não encontrado!");
        }
        return autor.get();
    }

    public List<Autor> getAll(){
        return repository.findAll();
    }

    public Autor update(Autor autor){
        Optional<Autor> novoAutor = repository.findById(autor.getId());
        if (novoAutor.isEmpty()){
            throw new EntityNotFound("Autor de ID: "+autor.getId()+" não encontrado!");
        }
        updateAutor(novoAutor, autor);
        return repository.save(novoAutor.get());
    }

    private void updateAutor(Optional<Autor> novoAutor, Autor autor) {
        novoAutor.get().setNome(autor.getNome());
    }
}
