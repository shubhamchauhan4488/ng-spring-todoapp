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
import java.util.Optional;

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

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @Valid @RequestBody Todo todo){
        Optional<Todo> todos = todoRepository.findById(id);
        System.out.println(String.format("todos found by id : %s",todos.toString()));
        return todos.map(item -> {
            item.setTitle(todo.getTitle());
            item.setCompleted(todo.getCompleted());
            Todo updatedTodo = todoRepository.save(item);
            return ResponseEntity.ok().body(updatedTodo);
        }).orElse(ResponseEntity.notFound().build());
    }
}
