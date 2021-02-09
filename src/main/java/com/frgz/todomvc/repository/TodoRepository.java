package com.frgz.todomvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.frgz.todomvc.domain.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
