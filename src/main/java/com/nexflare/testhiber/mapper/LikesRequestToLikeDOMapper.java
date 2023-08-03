package com.nexflare.testhiber.mapper;

import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LikesRequestToLikeDOMapper implements IRequestToDOMapper<AddLikeRequestObject, Likes>{
    @Override
    public Likes map(AddLikeRequestObject obj) {
        User user = User.builder().id(obj.getUserId()).build();
        Blog blog = Blog.builder().blogId(obj.getBlogId()).build();
        return Likes.builder().blog(blog).user(user).build();
    }
}
