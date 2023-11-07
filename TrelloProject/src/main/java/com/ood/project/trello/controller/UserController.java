package com.ood.project.trello.controller;

import com.ood.project.trello.apimodel.responsemodel.AppResponse;
import com.ood.project.trello.model.User;
import com.ood.project.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<AppResponse> getAllUsers(){
        List<User> allUsers = new ArrayList<>();

        allUsers = userService.getAllUsers();

        return ResponseEntity.ok(new AppResponse("User found successfully", allUsers, HttpStatus.CREATED));
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppResponse> getUser(@PathVariable String username) {
        User user = userService.getUser(username);

        if(user != null){
            return ResponseEntity.ok(new AppResponse("User found successfully", user, HttpStatus.CREATED));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppResponse("User not found", null, HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/")
    public ResponseEntity<AppResponse> addUser(@RequestBody User user) {
        if(userService.createUser(user) != null){
            return ResponseEntity.ok(new AppResponse("User create successfully", user, HttpStatus.CREATED));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppResponse("User creation failed", null, HttpStatus.BAD_REQUEST));
    }


    @PostMapping("/addMultiple")
    public ResponseEntity<AppResponse> addMultipleUsers(@RequestBody List<User> users) {
        List<User> addedUsers = new ArrayList<>();
        for (User user : users) {
            User addedUser = userService.createUser(user);
            addedUsers.add(addedUser);
        }

        return ResponseEntity.ok(new AppResponse("User found successfully", addedUsers, HttpStatus.CREATED));
    }



    @PutMapping("/{username}")
    public ResponseEntity<AppResponse> updateUser(@PathVariable String username, @RequestBody User user) {
        User updatedUser = userService.updateUser(username, user);

        if(updatedUser != null){
            return ResponseEntity.ok(new AppResponse("User updated successfully", updatedUser, HttpStatus.CREATED));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppResponse("User updation failed", null, HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping ("/{username}")
    public ResponseEntity<AppResponse> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(new AppResponse("User deleted successfully", null , HttpStatus.CREATED));
    }
}