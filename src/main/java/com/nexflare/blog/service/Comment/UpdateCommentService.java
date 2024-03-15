package com.nexflare.blog.service.Comment;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Comments;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Comment.UpdateCommentRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Comment.CommentResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
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
