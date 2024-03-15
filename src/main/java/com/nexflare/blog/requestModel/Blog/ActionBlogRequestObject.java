package com.nexflare.blog.requestModel.Blog;

import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ActionBlogRequestObject extends AbstractRequestObject {

    private UUID blogId;
    private boolean approved;
}
