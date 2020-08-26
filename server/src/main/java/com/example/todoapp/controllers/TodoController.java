package com.example.todoapp.controllers;

import com.example.todoapp.Repository.TodoRepository;
import com.example.todoapp.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @PostMapping("/todos")
    public ResponseEntity<Object> createTodo(@Valid @RequestBody Todo todo) {
        System.out.println(todo);
        todo.setCompleted(false);
        Todo savedTodo = todoRepository.save(todo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTodo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todos/{id}")
    public void deleteUser(@PathVariable int id) {
        System.out.println(String.format("id",id));
        todoRepository.deleteById(id);
    }
}
