package com.ankurray.ToDo.repository;


import com.ankurray.ToDo.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDoList, Long> {}
