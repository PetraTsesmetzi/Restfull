package com.example.myfirstrestapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity//damit sagen wir  hybernate erstelle  mir eine Tabelle
public class Todo {
    @Id//das setzt id auf Primarykey
    @GeneratedValue(strategy = GenerationType.AUTO)//autoincrement +1 des primary keys
    private Integer id;
    private String description;
    private boolean isDone;

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setIsDone(boolean isDone){
        this.isDone=isDone;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

}
