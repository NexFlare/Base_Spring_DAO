package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.User.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    BaseHandler<CreateNewUserRequestObject> createNewUserService;

    @Autowired
    public UserController(BaseHandler<CreateNewUserRequestObject> createNewUserService) {
        this.createNewUserService = createNewUserService;
    }

    @GetMapping("/")
    public List<User> getUsers(UserDAO dao) {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public User getUserDetail(@PathVariable UUID id, UserDAO dao) {
        try{
            User user = dao.get(id);
            return user;
        } catch (DataNotFoundException exception) {
            return null;
        }
    }

    @PostMapping("/")
    public Response addUser(@RequestBody CreateNewUserRequestObject user) {
        return createNewUserService.handle(user);
    }

}
