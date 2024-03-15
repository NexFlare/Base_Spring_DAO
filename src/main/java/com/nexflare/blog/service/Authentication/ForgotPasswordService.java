package com.nexflare.blog.service.Authentication;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.helper.ObjectToMap;
import com.nexflare.blog.mapper.User.UserDOToUserResponseMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.ForgotPasswordRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.responseModel.UserResponseModel;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.UUID;

public class ForgotPasswordService extends AuthenticatedBaseHandler<ForgotPasswordRequestObject> {

    private UserDOToUserResponseMapper responseMapper;
    public ForgotPasswordService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, UserDOToUserResponseMapper responseMapper) {
        super(userDao, request);
        this.responseMapper = responseMapper;
    }

    @Override
    protected Response handleRequest(ForgotPasswordRequestObject object) throws AbstractException {
        UUID id = ((User) this.request.getSession().getAttribute("USER_OBJECT")).getId();
        User user = User.builder().email(object.getEmail()).password(object.getOldPassword()).id(id).build();
        Map<String, Object> map = new ObjectToMap<User>().getMap(user);
        user = this.userDao.getUniqueElementByQuery(map);
        user.setPassword(object.getNewPassword());
        this.userDao.update(user);
        this.request.getSession().setAttribute("USER_OBJECT", user);
        UserResponseModel response = responseMapper.map(user);
        return BaseResponseModel.<UserResponseModel>builder().response(response).code(200).build();
    }
}
