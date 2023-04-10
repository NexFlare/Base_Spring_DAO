package com.nexflare.testhiber.requestModel.Blog;

import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;
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
}
