package com.nexflare.testhiber.service.User;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.exceptions.IllegalArgumentException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CreateUserService extends BaseHandler<CreateNewUserRequestObject> {

    IRequestToDOMapper<CreateNewUserRequestObject, User> mapper;
    public CreateUserService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, IRequestToDOMapper<CreateNewUserRequestObject, User> mapper) {
        super(userDao, request);
        this.mapper = mapper;
    }

    @Override
    protected Response handleRequest(CreateNewUserRequestObject object) throws AbstractException {
        if(object.getPassword() == null ||
                object.getEmail() == null ||
                object.getLastName() == null ||
                object.getFirstName() == null) {
            throw new IllegalArgumentException("Mandatory fields are missing");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("email", object.getEmail());
        User user = null;
        try{
            user = this.getUserDao().getUniqueElementByQuery(map);
        } catch(DataNotFoundException ignored) {}
//        List<User> userList = this.getUserDao().getElementByQuery("email", object.getEmail());
        if(user != null) {
            throw new IllegalArgumentException("User already exist");
        }
        User userObj = mapper.map(object);
        this.getUserDao().add(userObj);
        return BaseResponseModel.<User>builder().response(userObj).code(200).build();
    }


}
