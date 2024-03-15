package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.helper.ObjectToMap;
import com.nexflare.blog.mapper.IDOToResponseMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Blog.GetBlogsResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GetPendingBlogs extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    private final AbstractDAL<Blog, UUID> blogDAL;
    private final IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper;
    public GetPendingBlogs(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Blog, UUID> blogDAL, IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper) {
        super(userDao, request);
        this.blogDAL = blogDAL;
        this.responseMapper = responseMapper;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        User user = getUserFromSession();
        Blog blog = Blog.builder().user(user).blogStatus(BlogStatus.PENDING).build();
        ObjectToMap<Blog> mapper = new ObjectToMap<>();
        Map<String, Object> map = mapper.getMap(blog);
        List<Blog> blogs = blogDAL.getElementsByQuery(map);
        return BaseResponseModel.<List<Blog>>builder().response(blogs).code(200).build();
    }
}
