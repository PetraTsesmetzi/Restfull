package com.example.myfirstrestapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
   @GetMapping("/greet")
    public ResponseEntity<String> hello(@RequestParam(value="name")String username){
       if(username.equals("admin"))
            return new ResponseEntity("hey du "+username, HttpStatus.OK);
       return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
    }
}
