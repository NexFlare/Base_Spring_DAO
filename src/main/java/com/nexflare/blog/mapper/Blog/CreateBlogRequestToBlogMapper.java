package com.nexflare.blog.mapper.Blog;

import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.requestModel.Blog.CreateBlogRequestObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateBlogRequestToBlogMapper implements IRequestToDOMapper<CreateBlogRequestObject, Blog> {
    @Override
    public Blog map(CreateBlogRequestObject obj) {
        return Blog.builder()
                .image(obj.getImageUrl())
                .title(obj.getTitle())
                .location(obj.getLocation())
                .text(obj.getText())
                .type(obj.getType())
                .user(obj.getUser())
                .build();
    }
}
