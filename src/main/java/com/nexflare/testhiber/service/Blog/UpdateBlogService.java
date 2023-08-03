package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Blog.UpdateBlogRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Hibernate;

import java.util.UUID;

public class UpdateBlogService extends AuthenticatedBaseHandler<UpdateBlogRequestObject> {

    private final AbstractDAL<Blog, UUID> blogDao;
    private final IRequestToDOMapper<UpdateBlogRequestObject, Blog> mapper;
    public UpdateBlogService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Blog, UUID> blogDao, IRequestToDOMapper<UpdateBlogRequestObject, Blog> mapper) {
        super(userDao, request);
        this.blogDao = blogDao;
        this.mapper = mapper;
    }

    @Override
    protected Response handleRequest(UpdateBlogRequestObject object) throws AbstractException {

        Blog dbBlog = blogDao.get(object.getBlogId());
        Blog blog = mapper.map(object);
        User user = this.getUserFromSession();
        if(!dbBlog.getUser().getId().equals(user.getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized request").build();
        }
        if(blog.getImage() != null) dbBlog.setImage(blog.getImage());
        if(blog.getLocation() != null) dbBlog.setLocation(blog.getLocation());
        if(blog.getText() != null) dbBlog.setText(blog.getText());
        if(blog.getTitle() != null) dbBlog.setTitle(blog.getTitle());

        blogDao.update(dbBlog);
//        Hibernate.initialize(dbBlog.getLikes());
        return BaseResponseModel.<Blog>builder().response(dbBlog).code(200).build();
    }
}
