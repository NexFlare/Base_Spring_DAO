package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.mapper.Blog.CreateBlogRequestToBlogMapper;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Configurable
public class CreateBlogService extends AuthenticatedBaseHandler<CreateBlogRequestObject> {

    private final AbstractDAL<Blog, UUID> blogDao;
    private final IRequestToDOMapper<CreateBlogRequestObject, Blog> mapper;

    @Autowired
    public CreateBlogService(AbstractDAL<User, UUID> userDao, HttpServletRequest request,
                             AbstractDAL<Blog, UUID> blogDao, CreateBlogRequestToBlogMapper mapper) {
        super(userDao, request);
        this.blogDao = blogDao;
        this.mapper = mapper;
    }
    @Override
    protected Response handleRequest(CreateBlogRequestObject object) {
        Blog blog = this.mapper.map(object);
        User userObj = this.getUserFromSession();
        if(object.getUser().getId() == null || !userObj.getId().equals(object.getUser().getId())){
            return BaseResponseModel.<Blog>builder().errorMessage("User not authorized").code(403).build();
        }
        blog.setBlogStatus(BlogStatus.PENDING);
        this.blogDao.add(blog);
        return BaseResponseModel.<Blog>builder().response(blog).code(200).build();
    }

}
