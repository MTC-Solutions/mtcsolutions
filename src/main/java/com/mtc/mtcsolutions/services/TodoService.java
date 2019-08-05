package com.mtc.mtcsolutions.services;

import com.mtc.mtcsolutions.models.Todo;
import com.mtc.mtcsolutions.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class TodoService {
    private final TodoRepository _todoRepository;

    @Autowired
    public  TodoService(TodoRepository todoRepository){
        _todoRepository = todoRepository;
    }

    @Async
    public CompletableFuture<List<Todo>> getTodos(){
        return CompletableFuture.completedFuture(_todoRepository.findAll());
    }

    @Async
    public CompletableFuture<Todo> getTodo(Long id){
        Optional<Todo> todo = _todoRepository.findById(id);
        return CompletableFuture.completedFuture(todo.orElse(null));
    }

    @Async
    public CompletableFuture<Todo> saveTodo(Todo todo){
        return CompletableFuture.completedFuture(_todoRepository.save(todo));
    }

    @Async
    public CompletableFuture<Todo> updateTodo(Long id, Todo todo){
        Optional<Todo> oldTodo = _todoRepository.findById(id);

        //Check if to do is present if available update if not then return null
       if (oldTodo.isPresent()){
           oldTodo.get().setTitle(todo.getTitle());
           oldTodo.get().setComplete(todo.getComplete());
           return CompletableFuture.completedFuture(_todoRepository.save(oldTodo.get()));
       }

       return null;
    }

    @Async
    public CompletableFuture<Todo> deleteTodo(Long id){
        Optional<Todo> todo = _todoRepository.findById(id);
        if (todo.isPresent()){
            _todoRepository.delete(todo.get());
            return CompletableFuture.completedFuture(todo.get());
        }

        return null;
    }
}
