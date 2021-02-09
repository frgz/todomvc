package com.frgz.todomvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.frgz.todomvc.repository.TodoRepository;
import org.junit.jupiter.api.Test;

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

}
