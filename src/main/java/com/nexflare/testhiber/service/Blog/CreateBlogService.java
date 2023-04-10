package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.mapper.Blog.CreateBlogRequestToBlogMapper;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Configurable
public class CreateBlogService extends BaseHandler<CreateBlogRequestObject> {

    AbstractDAO<Blog, UUID> blogDao;
    IRequestToDOMapper<CreateBlogRequestObject, Blog> mapper;


    @Autowired
    public CreateBlogService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, AbstractDAO<Blog, UUID> blogDao, CreateBlogRequestToBlogMapper mapper) {
        super(userDao, request);
        this.blogDao = blogDao;
        this.mapper = mapper;
    }
    @Override
    protected Response handleRequest(CreateBlogRequestObject object) {
        Blog blog = this.mapper.map(object);
        if(validateIsUserLoggedIn()) {
            this.blogDao.add(blog);
            return BaseResponseModel.<Blog>builder().response(blog).build();
        } else {
            return BaseResponseModel.
                    <Blog>builder()
                    .errorMessage("User not authenticated")
                    .code(401).build();
        }
    }

}
