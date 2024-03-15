package com.nexflare.blog.requestModel.Comment;

import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class CreateCommentRequestObject extends AbstractRequestObject {

    private String comment;
    private UUID userId;
    private UUID blogId;

}
