package com.example.myfirstrestapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TodoController {

    @Autowired//Field Dependency-Injection damit injiziert spring intern eine Objekt
    private TodoRepository todoRepository;

    @GetMapping("/todo")
    public ResponseEntity<String> get(@RequestParam(value = "id") int id) {

        Optional<Todo> todoInDb = todoRepository.findById(id);
        if(todoInDb.isPresent()){
            return new ResponseEntity(todoInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No Todo found with id "+id, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<Todo>>getAll(){
        Iterable<Todo>allTodosInDb=todoRepository.findAll();
        return new ResponseEntity<Iterable<Todo>>(allTodosInDb,HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> create(@RequestBody Todo newTodo) {
        //save to db
        todoRepository.save(newTodo);
        return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);

    }
}
