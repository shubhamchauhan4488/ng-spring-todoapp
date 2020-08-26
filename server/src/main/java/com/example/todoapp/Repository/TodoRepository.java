package com.example.todoapp.Repository;

import com.example.todoapp.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.mongodb.repository.MongoRepository;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
