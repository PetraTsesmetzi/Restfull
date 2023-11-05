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
        return new ResponseEntity<Todo>(newTodo, HttpStatus.CREATED);
    }
    @DeleteMapping("/todo")
    public ResponseEntity delete(@RequestParam(value="id")int id){
        Optional<Todo> todoInDB=todoRepository.findById(id);
        if(todoInDB.isPresent()){
            todoRepository.deleteById(id);
            return new ResponseEntity<>("Todo deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not such Todo found with id: "+id,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/todo")
    public ResponseEntity<Todo>edit(@RequestBody Todo editTodo){
        Optional<Todo>todoInDb=todoRepository.findById(editTodo.getId());
        if(todoInDb.isPresent()){
            //update
            Todo savedTodo=todoRepository.save(editTodo);
            return  new ResponseEntity<Todo>(savedTodo,HttpStatus.OK);
        }
        return new ResponseEntity("No such todo found with id: "+editTodo.getId(),HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/todo/setDone")
    public ResponseEntity<Todo>setDone(@RequestParam(value="isDone")boolean isDone,
                                       @RequestParam(value="id")int id) {
        Optional<Todo> todoInDb = todoRepository.findById(id);
        if (todoInDb.isPresent()) {
            //update
            todoInDb.get().setIsDone(isDone);
            Todo savedTodo = todoRepository.save(todoInDb.get());
            return new ResponseEntity("ok", HttpStatus.OK);


        }
        return new ResponseEntity("No such todo found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
