package com.ankurray.ToDo.controller;

import com.ankurray.ToDo.model.ToDoList;
import com.ankurray.ToDo.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ToDoController {

    private final TodoRepository repository;

    public ToDoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ToDoList> getAllToDoLists() {
        return repository.findAll();
    }

    @PostMapping
    public ToDoList createToDoList(@RequestBody ToDoList toDoList) {
        return repository.save(toDoList);
    }

    @PutMapping("/{id}")
    public ToDoList updateToDoList(@PathVariable Long id, @RequestBody ToDoList toDoList) {
        ToDoList existing = repository.findById(id).orElseThrow();
        existing.setTitle(toDoList.getTitle());
        existing.setCompleted(toDoList.isCompleted());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoList(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
