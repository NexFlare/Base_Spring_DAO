package com.nexflare.testhiber.responseModel.Blog;

import com.nexflare.testhiber.enums.BlogStatus;
import com.nexflare.testhiber.responseModel.AbstractResponseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GetBlogResponseModel extends AbstractResponseModel {


        private UUID blogId;
        private GetBlogResponseModel.UserDetail user;
        private String title;
        private String location;
        private BlogStatus status;
        private Integer likes;
        private String imageUrl;
        private boolean liked;
        private String text;
        private List<CommentDetail> comments;


    @Getter
    @Setter
    @Builder
    public static class UserDetail {
        private String firstName;
        private String lastName;
        private UUID userId;
    }

    @Getter
    @Setter
    @Builder
    public static class CommentDetail {
        private String firstName;
        private String lastName;
        private UUID commentId;
        private String comment;
    }
}