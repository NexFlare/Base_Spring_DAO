package com.nexflare.blog.service.User;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("GetUserByIDService")
public class GetUserByIDService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    public GetUserByIDService(AbstractDAL<User, UUID> userDao, HttpServletRequest request) {
        super(userDao, request);
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        User user = User.builder().id(object.getId()).build();
        user = this.userDao.get(user.getId());
        return BaseResponseModel.<User>builder().response(user).code(200).build();
    }
}
