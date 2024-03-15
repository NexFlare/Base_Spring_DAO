package com.nexflare.blog.service.Notification;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.Notification;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GetNotification extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    AbstractDAL<Notification, UUID> notificationDAL;
    public GetNotification(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Notification, UUID> notificationDAL) {
        super(userDao, request);
        this.notificationDAL = notificationDAL;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        User user = this.getUserFromSession();
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("seen", false);
        List<Notification> notificationList = this.notificationDAL.getElementsByQuery(map);
        return BaseResponseModel.<List<Notification>>builder().response(notificationList).code(200).build();
    }
}
