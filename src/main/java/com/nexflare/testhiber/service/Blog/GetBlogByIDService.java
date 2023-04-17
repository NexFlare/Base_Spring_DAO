package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("GetBlogService")
public class GetBlogByIDService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    AbstractDAL<Blog, UUID> blogDAO;
    public GetBlogByIDService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Blog, UUID> blogDAO) {
        super(userDao, request);
        this.blogDAO = blogDAO;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Blog blog = Blog.builder().blogId(object.getId()).build();
        blog = this.blogDAO.get(blog.getBlogId());
        return BaseResponseModel.<Blog>builder().response(blog).build();
    }
}
