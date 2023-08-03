package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.mapper.IDOToResponseMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Blog.GetBlogResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
