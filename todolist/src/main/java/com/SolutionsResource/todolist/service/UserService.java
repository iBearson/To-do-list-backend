package com.SolutionsResource.todolist.service;

import com.SolutionsResource.todolist.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId);
    String deleteUser(int id);
    User updateUser(int id, User updatedUser); // Add this line
}
