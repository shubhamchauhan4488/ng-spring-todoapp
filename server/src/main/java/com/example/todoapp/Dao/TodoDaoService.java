package com.example.todoapp.Dao;

import com.example.todoapp.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoDaoService extends MongoRepository<Todo, String> {
}
