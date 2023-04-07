package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private AbstractDAO<User, UUID> dao;

    @Autowired
    public UserController(AbstractDAO<User, UUID> dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public User getUserDetail(@PathVariable UUID id) {
        try{
            User user = this.dao.get(id);
            return user;
        } catch (DataNotFoundException exception) {
            return null;
        }
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user) {
        List<User> userList = this.dao.getElementByQuery("email", user.getEmail());
        if(userList != null && userList.size() != 0) {
            throw new Error("User already exist");
        }
        this.dao.add(user);
    }

}
