package com.example.myfirstrestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
   @GetMapping("/greet")
    public String hello(){

       return "hey du";
    }
}
