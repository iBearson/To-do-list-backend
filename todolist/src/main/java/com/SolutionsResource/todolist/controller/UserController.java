package com.SolutionsResource.todolist.controller;

import com.SolutionsResource.todolist.model.User;
import com.SolutionsResource.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        System.out.println("Received user with dueDate: " + user.getDueDate()); // Debug log
        userService.saveUser(user);
        return "New user is added";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {
        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            existingUser.setName(userDetails.getName());
            existingUser.setActions(userDetails.getActions());
            existingUser.setDueDate(userDetails.getDueDate()); // Update due date
            existingUser.setIsDone(userDetails.getIsDone()); // Update isDone status
            userService.saveUser(existingUser);
            return "User with ID " + userId + " updated successfully";
        } else {
            return "User with ID " + userId + " not found";
        }
    }

    // New endpoint to update only the isDone status of a task
    @PutMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable(value = "id") int userId, @RequestBody boolean isDone) {
        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            existingUser.setIsDone(isDone);
            userService.saveUser(existingUser);
            return "Task status of user with ID " + userId + " updated successfully to " + (isDone ? "done" : "pending");
        } else {
            return "User with ID " + userId + " not found";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
