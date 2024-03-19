package com.nexflare.blog.controller;

import com.nexflare.blog.dal.*;
import com.nexflare.blog.mapper.User.CreateUserRequestToUserMapper;
import com.nexflare.blog.mapper.User.GetUserRequestToUserMapper;
import com.nexflare.blog.mapper.User.UserDOToUserResponseMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.requestModel.User.ForgotPasswordRequestObject;
import com.nexflare.blog.requestModel.User.GetUserRequestObject;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.Authentication.ForgotPasswordService;
import com.nexflare.blog.service.Authentication.LoginService;
import com.nexflare.blog.service.Authentication.LogoutService;
import com.nexflare.blog.service.BaseHandler;
import com.nexflare.blog.service.User.CreateUserService;
import com.nexflare.blog.service.User.DeleteUserService;
import com.nexflare.blog.service.User.GetUserByIDService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "http://10.110.131.218:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("")
    public List<User> getUsers(UserDAL dao) {
        return dao.getAll();
    }

    @PostMapping("/login")
    public Response loginUser(@RequestBody GetUserRequestObject object, UserDAL userDAL, HttpServletRequest request, GetUserRequestToUserMapper userMapper) {
        BaseHandler<GetUserRequestObject> userRequestService = new LoginService(userDAL,request,userMapper);
        return userRequestService.handle(object);
    }

    @PostMapping("/logout")
    public Response logoutUser(UserDAL userDAL, HttpServletRequest request) {
        BaseHandler<GetByIdRequestObject> userRequestService = new LogoutService(userDAL,request);
        return userRequestService.handle(new GetByIdRequestObject());
    }

    @PostMapping("")
    public Response addUser(@RequestBody CreateNewUserRequestObject user, UserDAL userDAL, HttpServletRequest request, CreateUserRequestToUserMapper mapper) {
        BaseHandler<CreateNewUserRequestObject> createNewUserService = new CreateUserService(userDAL, request,mapper);
        return createNewUserService.handle(user);
    }

    @GetMapping("/{id}")
    public Response getUserDetail(@PathVariable UUID id, UserDAL userDAL, HttpServletRequest request) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        BaseHandler<GetByIdRequestObject> getUserService = new GetUserByIDService(userDAL, request);
        System.out.println(System.identityHashCode(userDAL));
        return getUserService.handle(obj);
    }

    @PutMapping("/forgotpassword")
    public Response forgotPassword(@RequestBody ForgotPasswordRequestObject obj, UserDAL userDAL, HttpServletRequest request, UserDOToUserResponseMapper responseMapper) {
        BaseHandler<ForgotPasswordRequestObject> handler = new ForgotPasswordService(userDAL,request,responseMapper);
        return handler.handle(obj);
    }

    @DeleteMapping("/")
    public Response deleteUser(UserDAL userDAL, HttpServletRequest request, BlogDAL blogDAL, CommentDAL commentDAL, LikesDAL likesDAL, NotificationDAL notificationDAL) {
        BaseHandler<GetByIdRequestObject> handler = new DeleteUserService(userDAL, request, blogDAL, commentDAL, likesDAL, notificationDAL);
        GetByIdRequestObject obj = new GetByIdRequestObject();
        return handler.handle(obj);
    }

}
