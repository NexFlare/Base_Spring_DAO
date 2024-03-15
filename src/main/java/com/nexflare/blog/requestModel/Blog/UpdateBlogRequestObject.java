package com.nexflare.blog.requestModel.Blog;

import com.nexflare.blog.enums.BlogType;
import com.nexflare.blog.requestModel.AbstractRequestObject;
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
