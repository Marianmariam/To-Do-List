package com.example.TodoManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TodoManager.data.ToDo;
import com.example.TodoManager.service.ToDoService;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping({"/", "viewList"})
    public String viewAllItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getAllItems());
        model.addAttribute("message", message);
        
        return "ViewList";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewList";
        }
        
        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewList";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        model.addAttribute("todo", new ToDo());
        
        return "AddItem";
    }

    @PostMapping("/saveItem")
    public String saveItem(ToDo todo, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewList";
        }
        
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addItem";
    }
    
    @GetMapping("/editItem/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.getItemById(id));
        
        return "EditItem";
    }

    @PostMapping("/editSaveItem")
    public String editSaveItem(ToDo todo, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewList";
        }
        
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editItem/" + todo.getId();
    }
    
    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewList";
        }
        
        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewList";
    }
}