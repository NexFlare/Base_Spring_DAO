package com.nexflare.testhiber.requestModel.Blog;

import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ActionBlogRequestObject extends AbstractRequestObject {

    private UUID blogId;
    private boolean approved;
}
