package com.ood.project.trello.service;

import com.ood.project.trello.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User createUser(User user);

    public User getUser(String username);

    public List<User> getAllUsers();

    public User updateUser(String username, User updatedUser);

    public void deleteUser(String username);

    public User getUserById(String user);
}

