package com.nexflare.testhiber.service.Comment;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CreateCommentService extends AuthenticatedBaseHandler<CreateCommentRequestObject> {

    IRequestToDOMapper<CreateCommentRequestObject, Comments> commentMapper;
    AbstractDAL<Comments, UUID> commentDAO;

    public CreateCommentService(AbstractDAL<User, UUID> userDao, HttpServletRequest request,
                                IRequestToDOMapper<CreateCommentRequestObject, Comments> commentMapper,
                                AbstractDAL<Comments, UUID> commentDAO) {
        super(userDao, request);
        this.commentMapper = commentMapper;
        this.commentDAO = commentDAO;
    }

    @Override
    protected Response handleRequest(CreateCommentRequestObject object) throws AbstractException {
        Comments commentObj = commentMapper.map(object);
        this.commentDAO.add(commentObj);
        return BaseResponseModel.<Comments>builder().response(commentObj).build();
    }
}
