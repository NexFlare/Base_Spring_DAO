package com.nexflare.testhiber.mapper.Comment;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.testhiber.requestModel.Comment.UpdateCommentRequestObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateCommentRequestToCommentMapper implements IRequestToDOMapper<UpdateCommentRequestObject, Comments> {
    @Override
    public Comments map(UpdateCommentRequestObject obj) {
        return Comments.builder().id(obj.getCommentId()).comment(obj.getComment()).build();
    }
}