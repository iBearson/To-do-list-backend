package com.SolutionsResource.todolist.service;

import com.SolutionsResource.todolist.model.User;
import com.SolutionsResource.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        user.setTimestamp(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public String deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User not found.";
        }
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setActions(updatedUser.getActions());
            user.setTimestamp(LocalDateTime.now()); // Update timestamp
            return userRepository.save(user);
        } else {
            return null; // or throw an exception indicating user not found
        }
    }
}
