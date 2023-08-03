package com.nexflare.testhiber.mapper.Blog;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import lombok.Data;
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
