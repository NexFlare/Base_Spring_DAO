package com.nexflare.blog.mapper.Comment;

import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Comments;
import com.nexflare.blog.requestModel.Comment.UpdateCommentRequestObject;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommentRequestToCommentMapper implements IRequestToDOMapper<UpdateCommentRequestObject, Comments> {
    @Override
    public Comments map(UpdateCommentRequestObject obj) {
        return Comments.builder().id(obj.getCommentId()).comment(obj.getComment()).build();
    }
}