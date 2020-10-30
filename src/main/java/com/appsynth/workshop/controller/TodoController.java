package com.appsynth.workshop.controller;

import com.appsynth.workshop.dto.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @GetMapping("{id}")
    public Todo getTodoById(@PathVariable("id") final String id) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String createTodo(@RequestBody final Todo body) {
        Todo newTodo = Todo.builder()
                .id(UUID.randomUUID().toString())
                .text(body.getText())
                .build();
        todoList.add(newTodo);
        return "OK";
    }

    @PutMapping("/{id}")
    public String updateTodo(@PathVariable("id") final String id, @RequestBody final Todo body) {
        todoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setText(body.getText()));
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable("id") String id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
        return "OK";
    }
}