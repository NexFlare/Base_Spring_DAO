package com.nexflare.blog.service.User;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.GetUserRequestObject;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
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
