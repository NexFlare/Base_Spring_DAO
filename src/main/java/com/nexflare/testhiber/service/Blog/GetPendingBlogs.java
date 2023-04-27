package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.enums.BlogStatus;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.mapper.IDOToResponseMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Blog.GetBlogsResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
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
