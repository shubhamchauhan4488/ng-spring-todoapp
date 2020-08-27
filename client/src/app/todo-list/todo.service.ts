import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  //CRUD SERVICES
  createTodo(todoData: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.baseUrl + '/api/todos/', todoData);
  }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.baseUrl + '/api/todos/')
  }

  updateTodo(todoData: Todo): Observable<Todo> {
    return this.http.put<Todo>(this.baseUrl + '/api/todos/' + todoData.id, todoData);
  }

  deleteTodo(id: BigInteger): Observable<Todo> {
    return this.http.delete<Todo>(this.baseUrl + '/api/todos/' + id);
  }
}