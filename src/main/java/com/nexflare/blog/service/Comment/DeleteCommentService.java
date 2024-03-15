package com.nexflare.blog.service.Comment;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.dal.IDataRetrieval;
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

@Service
public class DeleteCommentService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    IDataRetrieval<Comments, UUID> commentsDAL;
    public DeleteCommentService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, IDataRetrieval<Comments, UUID> commentsDAL) {
        super(userDao, request);
        this.commentsDAL = commentsDAL;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Comments commentsObj = commentsDAL.get(object.getId());
        if(!commentsObj.getUser().getId().equals(this.getUserFromSession().getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized request").build();
        }
        commentsDAL.delete(commentsObj);
        return BaseResponseModel.builder().code(200).response("Comment deleted successfully").build();
    }
}
