package com.ood.project.trello.service.impl;

import com.ood.project.trello.model.User;
import com.ood.project.trello.repository.UserRepository;
import com.ood.project.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        User dbUser = getUser(user.getUserName());

        if(dbUser == null){
            return userRepository.save(user);
        }

        return null;

    }

    public User getUser(String username)  {
        return userRepository.findByUserName(username);
    }

    public User getUserById(String uId)  {
        return userRepository.findByUserId(uId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll() ;
    }


    public User updateUser(String username, User updatedUser) {
        User user = getUser(username);

        if(user == null){
            return null;
        }

        user.setUserName(updatedUser.getUserName());
        user.setUserRole(updatedUser.getUserRole());
        return userRepository.save(user);
    }

    public void deleteUser(String username){
        User user = getUser(username);

        if(user != null) {
            userRepository.delete(user);
        }
    }


    public static void main(String[] args) {
        UserServiceImpl uim = new UserServiceImpl();
        System.out.println(uim.getUser("ringo"));
    }
}
