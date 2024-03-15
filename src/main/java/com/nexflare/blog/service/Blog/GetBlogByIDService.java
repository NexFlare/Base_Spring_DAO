package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.mapper.IDOToResponseMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Blog.GetBlogResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service("GetBlogService")
public class GetBlogByIDService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    AbstractDAL<Blog, UUID> blogDAO;
    IDOToResponseMapper<Blog, GetBlogResponseModel> responseMapper;
    public GetBlogByIDService(AbstractDAL<User, UUID> userDao, HttpServletRequest request,
                              AbstractDAL<Blog, UUID> blogDAO, IDOToResponseMapper<Blog, GetBlogResponseModel> responseMapper) {
        super(userDao, request);
        this.blogDAO = blogDAO;
        this.responseMapper = responseMapper;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        Blog blog = Blog.builder().blogId(object.getId()).build();
        blog = this.blogDAO.get(blog.getBlogId());
        AtomicBoolean liked= new AtomicBoolean(false);
        blog.getLikes().forEach(like -> {
            if(like.getUser().getId().equals(this.getUserFromSession().getId())) liked.set(true);
        });
        GetBlogResponseModel response = responseMapper.map(blog);
        response.setLiked(liked.get());
        return BaseResponseModel.<GetBlogResponseModel>builder().response(response).code(200).build();
    }
}
