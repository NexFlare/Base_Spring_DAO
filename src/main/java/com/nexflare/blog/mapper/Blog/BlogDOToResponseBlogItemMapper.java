package com.nexflare.blog.mapper.Blog;

import com.nexflare.blog.mapper.IDOToResponseMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.responseModel.Blog.GetBlogsResponseModel;
import org.springframework.stereotype.Component;

@Component
public class BlogDOToResponseBlogItemMapper implements IDOToResponseMapper<Blog, GetBlogsResponseModel.BlogItem> {
    @Override
    public GetBlogsResponseModel.BlogItem map(Blog obj) {
        User user = obj.getUser();
        GetBlogsResponseModel.UserDetail userObj = GetBlogsResponseModel.UserDetail
                .builder()
                .userId(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
        GetBlogsResponseModel.BlogItem blogItem = GetBlogsResponseModel.BlogItem
                .builder()
                .user(userObj)
                .blogId(obj.getBlogId())
                .location(obj.getLocation())
                .title(obj.getTitle())
                .status(obj.getBlogStatus())
                .likes(obj.getLikes().size())
                .imageUrl(obj.getImage())
                .text(obj.getText())
                .build();
        return blogItem;
    }
}
