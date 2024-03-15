package com.nexflare.blog.service.Comment;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.Comments;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("GetCommentService")
public class GetCommentService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    AbstractDAL<Comments, UUID> commentDAO;
    public GetCommentService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Comments, UUID> commentDAO) {
        super(userDao, request);
        this.commentDAO = commentDAO;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Comments commentObj = this.commentDAO.get(object.getId());
        return BaseResponseModel.<Comments>builder().response(commentObj).code(200).build();
    }
}
