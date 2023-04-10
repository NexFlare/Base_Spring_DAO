package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
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
    BaseHandler<GetUserRequestObject> getUserService;

    @Autowired
    public UserController(BaseHandler<CreateNewUserRequestObject> createNewUserService,
                          BaseHandler<GetUserRequestObject> getUserService) {
        this.createNewUserService = createNewUserService;
        this.getUserService = getUserService;
    }

    @GetMapping("/")
    public List<User> getUsers(UserDAO dao) {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public Response getUserDetail(@PathVariable UUID id, UserDAO dao) {
        GetUserRequestObject obj = new GetUserRequestObject(id);
        return this.getUserService.handle(obj);
    }

    @PostMapping("/")
    public Response addUser(@RequestBody CreateNewUserRequestObject user) {
        return createNewUserService.handle(user);
    }

}
