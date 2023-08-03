package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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
