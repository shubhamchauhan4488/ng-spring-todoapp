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
  editing: boolean = false;
  editingTodo: Todo = new Todo();

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

  clearEditing(): void {
    this.editingTodo = new Todo();
    this.editing = false;
  }

  deleteTodo(id: BigInteger): void {
    this.todoService.deleteTodo(id)
    .subscribe(() => {
      this.todos = this.todos.filter(todo => todo.id != id);
    });
  }

  editTodo(todoData: Todo): void {
    this.editing = true;
    Object.assign(this.editingTodo, todoData);
  }

  updateTodo(todoData: Todo): void {
    console.log(todoData);
    this.todoService.updateTodo(todoData)
    .subscribe(updatedTodo => {
      let existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
      Object.assign(existingTodo, updatedTodo);
      this.clearEditing();
    });
  }

  toggleCompleted(todoData: Todo): void {
    todoData.completed = !todoData.completed;
    this.todoService.updateTodo(todoData)
    .subscribe(updatedTodo => {
      let existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
      Object.assign(existingTodo, updatedTodo);
    });
  }

}
