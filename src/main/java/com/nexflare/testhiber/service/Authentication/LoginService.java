package com.nexflare.testhiber.service.Authentication;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service("LoginService")
public class LoginService extends BaseHandler<GetUserRequestObject> {

    IRequestToDOMapper<GetUserRequestObject, User> userMapper;
    public LoginService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, IRequestToDOMapper<GetUserRequestObject, User> userMapper) {
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
            return BaseResponseModel.<User>builder().code(200).build();
        } catch(DataNotFoundException e) {
            return BaseResponseModel.<User>builder().code(400).build();
        }
    }
}
