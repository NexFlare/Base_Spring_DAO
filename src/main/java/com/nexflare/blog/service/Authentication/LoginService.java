package com.nexflare.blog.service.Authentication;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.exceptions.DataNotFoundException;
import com.nexflare.blog.helper.ObjectToMap;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.GetUserRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service("LoginService")
public class LoginService extends BaseHandler<GetUserRequestObject> {

    IRequestToDOMapper<GetUserRequestObject, User> userMapper;
    public LoginService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, IRequestToDOMapper<GetUserRequestObject, User> userMapper) {
        super(userDao, request);
        this.userMapper = userMapper;
    }

    @Override
    protected Response handleRequest(GetUserRequestObject object) throws AbstractException {
        User user = userMapper.map(object);
        ObjectToMap<User> toMap = new ObjectToMap<>();
        Map<String, Object> map = toMap.getMap(user);
        try{
            user = this.userDao.getUniqueElementByQuery(map);
            HttpSession session = this.getRequest().getSession(true);
            session.setAttribute("USER_OBJECT", user);
            return BaseResponseModel.<User>builder().response(user).code(200).build();
        } catch(DataNotFoundException e) {
            return BaseResponseModel.<User>builder().code(400).build();
        }
    }
}
