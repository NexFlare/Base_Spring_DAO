package com.nexflare.testhiber.requestModel.Blog;

import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBlogRequestObject extends AbstractRequestObject {
    private UUID blogId;
    private String title;
    private String text;
    private String location;
    private BlogType type;
    private String imageUrl;
}
