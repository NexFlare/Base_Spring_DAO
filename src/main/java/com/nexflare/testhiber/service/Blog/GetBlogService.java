package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.enums.BlogStatus;
import com.nexflare.testhiber.enums.UserType;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.mapper.IDOToResponseMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Blog.GetBlogsResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetBlogService extends AuthenticatedBaseHandler<GetBlogRequestObject> {

    AbstractDAL<Blog, UUID> blogDao;
    private final IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper;

    public GetBlogService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Blog, UUID> blogDao, IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> responseMapper) {
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
        List<Blog> blogs = this.blogDao.getElementsByQuery(map);
        map.remove("blogStatus");
        map.put("user", user);
        List<Blog> userBlogs = this.blogDao.getElementsByQuery(map);
        Set<Blog> blogSet = new HashSet<>(userBlogs);
        blogSet.addAll(blogs);
        blogs = blogSet.stream().toList();

        //Create response model
        List<GetBlogsResponseModel.BlogItem> blogsResponseList = new ArrayList<>();
        for(int i =0;i<blogs.size();i++) {
            blogsResponseList.add(responseMapper.map(blogs.get(i)));
            List<Likes> likesArray = blogs.get(i).getLikes();
            for (Likes like : likesArray) {
                if (like.getUser().getId().equals(user.getId())) {
                    blogsResponseList.get(i).setLiked(true);
                    break;
                }
            }
        }

        GetBlogsResponseModel responseModel = GetBlogsResponseModel.builder().blogs(blogsResponseList).build();

        return BaseResponseModel.<GetBlogsResponseModel>builder().response(responseModel).build();
    }
}
