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
