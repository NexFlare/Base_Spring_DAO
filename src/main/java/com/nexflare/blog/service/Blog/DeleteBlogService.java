package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.Likes;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class DeleteBlogService extends AuthenticatedBaseHandler<GetByIdRequestObject> {
    AbstractDAL<Blog, UUID> blogDAL;
    AbstractDAL<Likes, UUID> likesDAL;
    public DeleteBlogService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Blog, UUID> blogDAL, AbstractDAL<Likes, UUID> likesDAL) {
        super(userDao, request);
        this.blogDAL = blogDAL;
        this.likesDAL = likesDAL;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Blog blog = blogDAL.get(object.getId());
        if(!blog.getUser().getId().equals(this.getUserFromSession().getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized request").build();
        }
        blogDAL.delete(blog);

        return BaseResponseModel.builder().code(200).response("Successfully deleted blog").build();
    }
}
