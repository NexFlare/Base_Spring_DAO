package com.nexflare.blog.responseModel.Blog;

import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.responseModel.AbstractResponseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GetBlogsResponseModel extends AbstractResponseModel {

    private List<BlogItem> blogs;

    @Getter
    @Setter
    @Builder
    public static class BlogItem {
        private UUID blogId;
        private UserDetail user;
        private String title;
        private String location;
        private BlogStatus status;
        private Integer likes;
        private String imageUrl;
        private boolean liked;
        private String text;
    }

    @Getter
    @Setter
    @Builder
    public static class UserDetail {
        private String firstName;
        private String lastName;
        private UUID userId;
    }
}
