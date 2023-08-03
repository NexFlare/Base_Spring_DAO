package com.nexflare.testhiber.service;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public abstract class AuthenticatedBaseHandler<T extends AbstractRequestObject> extends BaseHandler<T> {
    public AuthenticatedBaseHandler(AbstractDAL<User, UUID> userDao, HttpServletRequest request) {
        super(userDao, request);
    }

    @Override
    public Response handle(T object) {
        try {
            if(!this.validateIsUserLoggedIn()) {
                return BaseResponseModel.builder().code(401).errorMessage("User not authenticated").build();
            }
            return this.handleRequest(object);
        } catch (AbstractException exception) {
            String message = exception.getMessage();
            return BaseResponseModel.builder().errorMessage(message).code(400).build();
        }
    }
}
