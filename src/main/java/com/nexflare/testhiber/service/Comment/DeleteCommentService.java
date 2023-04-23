package com.nexflare.testhiber.service.Comment;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.dal.IDataRetrieval;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
