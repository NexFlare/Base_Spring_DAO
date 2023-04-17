package com.nexflare.testhiber.service.Comment;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Comment.UpdateCommentRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Comment.CommentResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class UpdateCommentService extends AuthenticatedBaseHandler<UpdateCommentRequestObject>{

    AbstractDAL<Comments, UUID> commentDao;
    IRequestToDOMapper<UpdateCommentRequestObject, Comments> mapper;
    public UpdateCommentService(AbstractDAL<User, UUID> userDao, HttpServletRequest request,
                                AbstractDAL<Comments, UUID> commentDao, IRequestToDOMapper<UpdateCommentRequestObject, Comments> mapper) {
        super(userDao, request);
        this.commentDao = commentDao;
        this.mapper = mapper;
    }

    @Override
    protected Response handleRequest(UpdateCommentRequestObject object) throws AbstractException {
        Comments commentObj = this.commentDao.get(object.getCommentId());
        commentObj.setComment(object.getComment());
        this.commentDao.update(commentObj);
        CommentResponseModel responseModel = CommentResponseModel.builder()
                .comment(commentObj.getComment())
                .id(commentObj.getId())
                .blogId(commentObj.getBlog().getBlogId())
                .userId(commentObj.getUser().getId())
                .build();
        return BaseResponseModel.<CommentResponseModel>builder().response(responseModel).code(200).build();
    }
}
