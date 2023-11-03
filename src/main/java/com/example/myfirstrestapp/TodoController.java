package com.example.myfirstrestapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {
   @GetMapping("/todo")
    public ResponseEntity<String> get(@RequestParam(value="id")int id){
        Todo newTodo=new Todo();
         newTodo.setId(id);
         newTodo.setDescription("Einkaufen");
         newTodo.setIsDone(true);
       return new ResponseEntity(newTodo, HttpStatus.OK);
    }
    @PostMapping("/todo")
    public ResponseEntity<Todo>create(@RequestBody Todo newTodo){
       //save to db
        return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);

    }
}
