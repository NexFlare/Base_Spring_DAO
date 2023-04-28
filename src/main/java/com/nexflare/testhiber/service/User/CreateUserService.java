package com.nexflare.testhiber.service.User;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.exceptions.DatabaseException;
import com.nexflare.testhiber.exceptions.IllegalArgumentException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
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
