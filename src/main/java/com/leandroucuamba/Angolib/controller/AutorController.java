package com.leandroucuamba.Angolib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leandroucuamba.Angolib.entity.Autor;
import com.leandroucuamba.Angolib.service.AutorService;

import java.util.List;

@RestController
@RequestMapping(value = "/autor")
public class AutorController {
    @Autowired
    private AutorService service;

    @PostMapping
    public ResponseEntity<Autor> create(@RequestBody Autor autor){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(autor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Autor> getId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getId(id));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor autor){
        autor.setId(id);
        return ResponseEntity.ok().body(service.update(autor));
    }

}
