package com.nexflare.blog.mapper.Comment;

import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.Comments;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Comment.CreateCommentRequestObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
public class CreateCommentRequestToCommentMapper implements IRequestToDOMapper<CreateCommentRequestObject, Comments> {
    @Override
    public Comments map(CreateCommentRequestObject obj) {
        User user = User.builder().id(obj.getUserId()).build();
        Blog blog = Blog.builder().blogId(obj.getBlogId()).build();
        return Comments.builder().comment(obj.getComment()).user(user).blog(blog).timestamp(new Date()).build();
    }
}
