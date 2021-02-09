package com.frgz.todomvc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frgz.todomvc.domain.Todo;
import com.frgz.todomvc.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class TodoController {

  private final TodoRepository todoRepository;

  @GetMapping
  public ResponseEntity<List<Todo>> find() {
    final List<Todo> todos = StreamSupport.stream(this.todoRepository.findAll().spliterator(), false).collect(Collectors.toList());
    return ResponseEntity.ok(todos);
  }

}
