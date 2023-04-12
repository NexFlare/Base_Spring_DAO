package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GetBlogService extends AuthenticatedBaseHandler<GetBlogRequestObject> {

    AbstractDAO<Blog, UUID> blogDao;

    public GetBlogService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, AbstractDAO<Blog, UUID> blogDao) {
        super(userDao, request);
        this.blogDao = blogDao;
    }

    @Override
    protected Response handleRequest(GetBlogRequestObject object) throws AbstractException {
        ObjectToMap<GetBlogRequestObject> mapper = new ObjectToMap<>();
        Map<String, Object> map = mapper.getMap(object);
        Blog blog = this.blogDao.getUniqueElementByQuery(map);
        return BaseResponseModel.<Blog>builder().response(blog).build();
    }
}
