package com.nexflare.testhiber.mapper.Comment;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
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
