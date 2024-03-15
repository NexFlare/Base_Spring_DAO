package com.nexflare.blog.service.Authentication;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class LogoutService extends AuthenticatedBaseHandler<GetByIdRequestObject> {
    public LogoutService(AbstractDAL<User, UUID> userDao, HttpServletRequest request) {
        super(userDao, request);
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        this.getRequest().getSession().invalidate();
        return BaseResponseModel.<String>builder().response("User logged out successfully").code(200).build();
    }
}
