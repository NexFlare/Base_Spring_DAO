package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.mapper.User.CreateUserRequestToUserMapper;
import com.nexflare.testhiber.mapper.User.GetUserRequestToUserMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.Authentication.LoginService;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.User.CreateUserService;
import com.nexflare.testhiber.service.User.GetUserByIDService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/")
    public List<User> getUsers(UserDAO dao) {
        return dao.getAll();
    }

    @PostMapping("/login")
    public Response loginUser(@RequestBody GetUserRequestObject object, UserDAO userDao, HttpServletRequest request, GetUserRequestToUserMapper userMapper) {
        BaseHandler<GetUserRequestObject> userRequestService = new LoginService(userDao,request,userMapper);
        return userRequestService.handle(object);
    }

    @PostMapping("/")
    public Response addUser(@RequestBody CreateNewUserRequestObject user, UserDAO userDao, HttpServletRequest request, CreateUserRequestToUserMapper mapper) {
        BaseHandler<CreateNewUserRequestObject> createNewUserService = new CreateUserService(userDao, request,mapper);
        return createNewUserService.handle(user);
    }


    @GetMapping("/{id}")
    public Response getUserDetail(@PathVariable UUID id, UserDAO userDAO, HttpServletRequest request) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        BaseHandler<GetByIdRequestObject> getUserService = new GetUserByIDService(userDAO, request);
        System.out.println(System.identityHashCode(userDAO));
        return getUserService.handle(obj);
    }

}
