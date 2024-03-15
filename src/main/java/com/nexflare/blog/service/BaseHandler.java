package com.nexflare.blog.service;


import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.AbstractRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@Setter
public abstract class BaseHandler<T extends AbstractRequestObject> {

    protected AbstractDAL<User, UUID> userDao;

    protected HttpServletRequest request;
    public BaseHandler(AbstractDAL<User, UUID> userDao, HttpServletRequest request) {
        this.request = request;
        this.userDao = userDao;
    }

    protected boolean validateIsUserLoggedIn() {
        HttpSession session =  this.request.getSession();
        if(session.getAttribute("USER_OBJECT") == null) return false;
        User user = (User) session.getAttribute("USER_OBJECT");
        return user != null && this.userDao.get(user.getId()) != null;
    }

    public Response handle(T object) {
        try {
            return this.handleRequest(object);
        } catch (AbstractException exception) {
            String message = exception.getMessage();
            return BaseResponseModel.builder().errorMessage(message).code(400).build();
        }
    }

    protected User getUserFromSession() {
        User user = (User) this.request.getSession().getAttribute("USER_OBJECT");
        return user;
    }

    protected abstract Response handleRequest(T object) throws AbstractException;

}
