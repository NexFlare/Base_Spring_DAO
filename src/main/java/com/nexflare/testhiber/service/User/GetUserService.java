package com.nexflare.testhiber.service.User;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("GetUserService")
public class GetUserService extends AuthenticatedBaseHandler<GetUserRequestObject> {
    public GetUserService(AbstractDAL<User, UUID> userDao, HttpServletRequest request) {
        super(userDao, request);
    }

    @Override
    protected Response handleRequest(GetUserRequestObject object) throws AbstractException {
        return null;
    }
}
