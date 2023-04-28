package com.nexflare.testhiber.controller;

import com.nexflare.testhiber.dal.NotificationDAL;
import com.nexflare.testhiber.dal.UserDAL;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Notification.GetNotification;
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
