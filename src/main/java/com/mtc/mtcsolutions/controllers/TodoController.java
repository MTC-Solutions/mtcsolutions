package com.mtc.mtcsolutions.controllers;

import com.mtc.mtcsolutions.models.Todo;
import com.mtc.mtcsolutions.services.TodoService;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService _todoService;

    public TodoController(TodoService _todoService) {
        this._todoService = _todoService;
    }

    @Async
    @GetMapping
    public CompletableFuture<List<Todo>> getTodos(){
        return _todoService.getTodos();
    }

    @Async
    @GetMapping("{id}")
    public CompletableFuture<Todo> getTodo(@PathVariable Long id){
        return _todoService.getTodo(id);
    }

    @Async
    @PostMapping
    public CompletableFuture<Todo> saveTodo(@RequestBody Todo todo){
        return _todoService.saveTodo(todo);
    }

    @Async
    @PutMapping("{id}")
    public CompletableFuture<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        return _todoService.updateTodo(id, todo);
    }

    @Async
    @DeleteMapping("{id}")
    public CompletableFuture<Todo> deleteTodo(@PathVariable Long id){
        return _todoService.deleteTodo(id);
    }

}
