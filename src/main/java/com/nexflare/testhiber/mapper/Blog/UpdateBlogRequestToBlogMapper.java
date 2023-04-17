package com.nexflare.testhiber.mapper.Blog;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.requestModel.Blog.UpdateBlogRequestObject;
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
