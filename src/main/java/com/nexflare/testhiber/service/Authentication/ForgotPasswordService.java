package com.nexflare.testhiber.service.Authentication;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.mapper.User.UserDOToUserResponseMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.ForgotPasswordRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.responseModel.UserResponseModel;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
