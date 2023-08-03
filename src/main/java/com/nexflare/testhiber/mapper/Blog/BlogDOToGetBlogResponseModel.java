package com.nexflare.testhiber.mapper.Blog;

import com.nexflare.testhiber.mapper.IDOToResponseMapper;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.responseModel.Blog.GetBlogResponseModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogDOToGetBlogResponseModel implements IDOToResponseMapper<Blog, GetBlogResponseModel> {
    @Override
    public GetBlogResponseModel map(Blog obj) {
        User user = obj.getUser();
        GetBlogResponseModel.UserDetail userDetail= GetBlogResponseModel.UserDetail
                .builder()
                .userId(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
        List<GetBlogResponseModel.CommentDetail> commentDetailList = new ArrayList<>();

        for(Comments comment : obj.getComments()) {
            User commentUser = comment.getUser();
            GetBlogResponseModel.CommentDetail commentDetail = GetBlogResponseModel
                    .CommentDetail.builder()
                    .commentId(comment.getId())
                    .comment(comment.getComment())
                    .firstName(commentUser.getFirstName())
                    .lastName(commentUser.getLastName())
                    .userId(commentUser.getId())
                    .build();
            commentDetailList.add(commentDetail);
        }

        return GetBlogResponseModel.builder()
                .blogId(obj.getBlogId())
                .location(obj.getLocation())
                .title(obj.getTitle())
                .status(obj.getBlogStatus())
                .likes(obj.getLikes().size())
                .imageUrl(obj.getImage())
                .text(obj.getText())
                .comments(commentDetailList)
                .user(userDetail)
                .build();

    }
}
