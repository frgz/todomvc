package com.frgz.todomvc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frgz.todomvc.domain.Todo;
import com.frgz.todomvc.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(
    methods ={ POST, GET, OPTIONS, DELETE, PATCH},
    maxAge =3600,
    allowedHeaders = { "x-requested-with", "origin", "content-type", "accept", "accept-patch"},
    origins = "*"
)
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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Todo create(@RequestBody final Todo todo) {
    return this.todoRepository.save(todo);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    todoRepository.deleteAll();
  }

}
