package com.frgz.todomvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.frgz.todomvc.domain.Todo;
import com.frgz.todomvc.repository.TodoRepository;

@SpringBootApplication
public class TodoMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(final TodoRepository todoRepository) {
		return args -> {
			todoRepository.deleteAll();
			todoRepository.save(Todo.builder().title("Wake up").build());
			todoRepository.save(Todo.builder().title("Fall out of the bed").build());
			todoRepository.save(Todo.builder().title("Draga comb across your head").build());
		};
	}

}
