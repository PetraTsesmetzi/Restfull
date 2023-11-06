package com.example.myfirstrestapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //einzigartig und nicht null
    @Column(unique=true, nullable=false)
    private String email;

    @Column( nullable=false)
    private String password;

    @OneToMany //ein User hat mehrere todos
    @JoinColumn(name="userId")//welche todos stimmen mit der userId überein
    private Set<Todo> todos;

    @JsonIgnore //ignoriert wenn von eine JSON datei etwas gestetzt werden soll
    private String secret;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Todo> getTodos() {
        return todos;
    }
    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
