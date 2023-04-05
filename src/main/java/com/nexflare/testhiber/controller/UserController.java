package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private AbstractDAO<User> dao;

    @Autowired
    public UserController(AbstractDAO<User> dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return dao.getAll();
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
