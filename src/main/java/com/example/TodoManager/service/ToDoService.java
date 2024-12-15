package com.example.TodoManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TodoManager.data.ToDo;
import com.example.TodoManager.repo.ToDoRepo;

@Service
public class ToDoService {
    
    @Autowired
    ToDoRepo repo;
    
    public List<ToDo> getAllItems() {
        ArrayList<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(todo -> todoList.add(todo));
        return todoList;
    }
    
    public ToDo getItemById(Long id) {
        return repo.findById(id).orElse(null); // Modificat aici
    }
    
    public boolean updateStatus(Long id) {
        ToDo todo = getItemById(id);
        if (todo != null) {  // Verificăm dacă item-ul există
            todo.setStatus("Completed");
            repo.save(todo);
            return true;
        }
        return false;
    }
    
    public boolean saveOrUpdateItem(ToDo todo) {
        try {
            repo.save(todo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean deleteItem(Long id) {
        try {
            if (getItemById(id) != null) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}