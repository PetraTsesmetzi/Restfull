package com.example.myfirstrestapp;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,Integer> {

}
