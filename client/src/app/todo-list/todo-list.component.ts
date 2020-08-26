import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import {TodoService} from './todo.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  todos: Todo[];
  newTodo: Todo = new Todo();

  constructor(
    private todoService: TodoService,
  ) {}

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos()
    .subscribe(data => this.todos = data) 
  }

  createTodo(todoForm: NgForm) : void {
    console.log('todoForm',todoForm);

    this.todoService.createTodo(this.newTodo)
    .subscribe(data => {
      todoForm.reset();
      console.log('this.newTodo',this.newTodo)
      console.log('data',data)
      this.todos.unshift(data);
      return
    }) 
  }


}
