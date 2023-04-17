package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.enums.BlogStatus;
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
        if(validateIsUserLoggedIn()) {
            User userObj = this.getUserFromSession();
            if(object.getUser().getId() == null || !userObj.getId().equals(object.getUser().getId())){
                return BaseResponseModel.<Blog>builder().errorMessage("User not authorized").code(401).build();
            }
            blog.setBlogStatus(BlogStatus.PENDING);
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
