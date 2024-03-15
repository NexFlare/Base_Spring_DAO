package com.nexflare.blog.controller;

import com.nexflare.blog.dal.NotificationDAL;
import com.nexflare.blog.dal.UserDAL;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.BaseHandler;
import com.nexflare.blog.service.Notification.GetNotification;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://10.110.131.218:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @GetMapping("/")
    public Response getUserNotifications(UserDAL userDAL, HttpServletRequest request, NotificationDAL notificationDAL) {
        BaseHandler<GetByIdRequestObject> handler = new GetNotification(userDAL, request, notificationDAL);
        return handler.handle(new GetByIdRequestObject());
    }
}
