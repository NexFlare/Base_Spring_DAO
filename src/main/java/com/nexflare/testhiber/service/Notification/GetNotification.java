package com.nexflare.testhiber.service.Notification;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.pojo.Notification;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
