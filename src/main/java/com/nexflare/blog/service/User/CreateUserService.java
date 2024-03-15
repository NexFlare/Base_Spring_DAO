package com.nexflare.blog.service.User;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.exceptions.DatabaseException;
import com.nexflare.blog.exceptions.IllegalArgumentException;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CreateUserService extends BaseHandler<CreateNewUserRequestObject> {

    IRequestToDOMapper<CreateNewUserRequestObject, User> mapper;
    public CreateUserService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, IRequestToDOMapper<CreateNewUserRequestObject, User> mapper) {
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

        if(object.getFirstName().length() >255) throw new IllegalArgumentException("Fist name cannot be greater that 255 in length");
        if(object.getLastName().length() > 255) throw new IllegalArgumentException("Last name cannot be greater that 255 in length");
        if(object.getPassword().length() > 255) throw new IllegalArgumentException("Password cannot be greater than 255 in length");
//        if(new EmailValidator(object.getEmail()).isValid)

        User userObj = mapper.map(object);
        try{
            this.getUserDao().add(userObj);
        } catch(AbstractException e) {
            throw new DatabaseException(e.getMessage());
        } catch(Exception e) {
            throw new DatabaseException("Please enter valid details");
        }

        return BaseResponseModel.<User>builder().response(userObj).code(200).build();
    }


}
