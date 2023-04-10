package com.nexflare.testhiber.service;


import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
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

    protected AbstractDAO<User, UUID> userDao;

    protected HttpServletRequest request;
    public BaseHandler(AbstractDAO<User, UUID> userDao, HttpServletRequest request) {
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
            return BaseResponseModel.builder().errorMessage(message).build();
        }
    }

    protected abstract Response handleRequest(T object) throws AbstractException;

}
