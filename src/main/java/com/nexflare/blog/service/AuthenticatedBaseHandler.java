package com.nexflare.blog.service;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.AbstractRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
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
