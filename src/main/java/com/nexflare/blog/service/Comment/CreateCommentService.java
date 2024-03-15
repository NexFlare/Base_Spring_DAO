package com.nexflare.blog.service.Comment;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.Comments;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CreateCommentService extends AuthenticatedBaseHandler<CreateCommentRequestObject> {

    IRequestToDOMapper<CreateCommentRequestObject, Comments> commentMapper;
    AbstractDAL<Comments, UUID> commentDAO;
    AbstractDAL<Blog, UUID> blogDAL;

    public CreateCommentService(AbstractDAL<User, UUID> userDao, HttpServletRequest request,
                                IRequestToDOMapper<CreateCommentRequestObject, Comments> commentMapper,
                                AbstractDAL<Comments, UUID> commentDAO, AbstractDAL<Blog, UUID> blogDAL) {
        super(userDao, request);
        this.commentMapper = commentMapper;
        this.commentDAO = commentDAO;
        this.blogDAL = blogDAL;
    }

    @Override
    protected Response handleRequest(CreateCommentRequestObject object) throws AbstractException {
        Comments commentObj = commentMapper.map(object);
        if(!object.getUserId().equals(this.getUserFromSession().getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized").build();
        }
        this.userDao.get(object.getUserId());
        this.blogDAL.get(object.getBlogId());
        this.commentDAO.add(commentObj);
        return BaseResponseModel.<Comments>builder().response(commentObj).code(200).build();
    }
}
