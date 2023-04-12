package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.User.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    BaseHandler<CreateNewUserRequestObject> createNewUserService;
    BaseHandler<GetByIdRequestObject> getUserService;

    BaseHandler<GetUserRequestObject> userRequestService;

    @Autowired
    public UserController(BaseHandler<CreateNewUserRequestObject> createNewUserService,
                          @Qualifier("GetUserByIDService") BaseHandler<GetByIdRequestObject> getUserService,
                          @Qualifier("LoginService") BaseHandler<GetUserRequestObject> userRequestService) {
        this.createNewUserService = createNewUserService;
        this.getUserService = getUserService;
        this.userRequestService = userRequestService;
    }

    @GetMapping("/")
    public List<User> getUsers(UserDAO dao) {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public Response getUserDetail(@PathVariable UUID id, UserDAO dao) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        return this.getUserService.handle(obj);
    }

    @PostMapping("/login")
    public Response loginUser(@RequestBody GetUserRequestObject object) {
        return userRequestService.handle(object);
    }

    @PostMapping("/")
    public Response addUser(@RequestBody CreateNewUserRequestObject user) {
        return createNewUserService.handle(user);
    }

//    @PostMapping("/")
//    public Response addUser(@RequestBody CreateNewUserRequestObject user, CreateUserService createNewUserService) {
//        return createNewUserService.handle(user);
//    }

}
