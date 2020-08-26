package com.example.todoapp.controllers;

import com.example.todoapp.Dao.TodoDaoService;
import com.example.todoapp.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoDaoService service;

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return service.findAll();
    }

}
