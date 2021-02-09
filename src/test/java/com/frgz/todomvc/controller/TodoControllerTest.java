package com.frgz.todomvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.frgz.todomvc.domain.Todo;
import com.frgz.todomvc.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TodoRepository todoRepository;

  @Test
  public void testGivenNoTodosGetAllReturnsEmptyArray() throws Exception {
    mvc.perform(get("/api/todo")) //
        .andExpect(status().isOk()) //
        .andExpect(content().string("[]"));
  }

  @Test
  public void testGivenAJSONPayloadShouldSaveATodo() throws Exception {
    final Todo todo = Todo.builder().title("Grab your hat").build();

    given(todoRepository.save(any(Todo.class))).willReturn(todo);

    mvc.perform(post("/api/todo")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\":\"Grab your hat\"}".getBytes())
        .characterEncoding("utf-8"))
        .andExpect(status().is(HttpStatus.CREATED.value()))
        .andExpect(jsonPath("$.title", is("Grab your hat")));
  }

  @Test
  public void testDeleteAllTodos() throws Exception {
    mvc.perform(delete("/api/todo")).andExpect(status().isOk());

    verify(todoRepository, atLeast(1)).deleteAll();
  }

}
