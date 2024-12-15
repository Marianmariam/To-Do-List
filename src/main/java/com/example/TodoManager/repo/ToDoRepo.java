package com.example.TodoManager.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TodoManager.data.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long>{
	
	
	
}
