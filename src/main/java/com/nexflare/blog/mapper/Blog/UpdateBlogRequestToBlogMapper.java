package com.nexflare.blog.mapper.Blog;

import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.requestModel.Blog.UpdateBlogRequestObject;
import org.springframework.stereotype.Component;

@Component
public class UpdateBlogRequestToBlogMapper implements IRequestToDOMapper<UpdateBlogRequestObject, Blog> {
    @Override
    public Blog map(UpdateBlogRequestObject obj) {
        return Blog.builder()
                .blogId(obj.getBlogId())
                .text(obj.getText())
                .location(obj.getLocation())
                .title(obj.getTitle())
                .image(obj.getImageUrl()).build();
    }
}
