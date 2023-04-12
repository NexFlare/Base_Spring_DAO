package com.nexflare.testhiber.service.Comment;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("GetCommentService")
public class GetCommentService extends BaseHandler<GetByIdRequestObject> {

    AbstractDAO<Comments, UUID> commentDAO;
    public GetCommentService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, AbstractDAO<Comments, UUID> commentDAO) {
        super(userDao, request);
        this.commentDAO = commentDAO;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Comments commentObj = this.commentDAO.get(object.getId());
        return BaseResponseModel.<Comments>builder().response(commentObj).build();
    }
}
