package com.nexflare.blog.requestModel.Blog;

import com.nexflare.blog.enums.BlogType;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetBlogRequestObject extends AbstractRequestObject {
    private UUID blogId;
    private String location;
    private BlogType type;
    private User user;
    private int page;
}
