package com.nexflare.testhiber.service.Authentication;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
