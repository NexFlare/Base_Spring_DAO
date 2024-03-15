package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.dal.BlogDAL;
import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.helper.ObjectToMap;
import com.nexflare.blog.mapper.IDOToResponseMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.Likes;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Blog.GetBlogsResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;

@Service
@RequestScope
public class GetBlogService extends AuthenticatedBaseHandler<GetBlogRequestObject> {

    BlogDAL blogDao;
    private final IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper;

    public GetBlogService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, BlogDAL blogDao, IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper) {
        super(userDao, request);
        this.blogDao = blogDao;
        this.responseMapper = responseMapper;
    }

    @Override
    protected Response handleRequest(GetBlogRequestObject object) throws AbstractException {

        ObjectToMap<GetBlogRequestObject> mapper = new ObjectToMap<>();
        Map<String, Object> map = mapper.getMap(object);
        User user = this.getUserFromSession();
        if(user.getUserType() != UserType.ADMIN) map.put("blogStatus", BlogStatus.APPROVED);
        if(map.containsKey("page")) map.remove("page");
//        List<Blog> blogs = this.blogDao.getElementsByQuery(map);
        List<Blog> blogs = this.blogDao.searchBlogElements(map, 1);
        List<GetBlogsResponseModel.BlogItem> blogsResponseList = new ArrayList<>();
        for(int i =0;i<blogs.size();i++) {
            blogsResponseList.add(responseMapper.map(blogs.get(i)));
            List<Likes> likesArray = blogs.get(i).getLikes();
            System.out.println(likesArray.size());
            for (Likes like : likesArray) {
                if (like.getUser().getId().equals(user.getId())) {
                    blogsResponseList.get(i).setLiked(true);
                    break;
                }
            }
        }

        GetBlogsResponseModel responseModel = GetBlogsResponseModel.builder().blogs(blogsResponseList).build();

        return BaseResponseModel.<GetBlogsResponseModel>builder().response(responseModel).code(200).build();
    }
}
