package com.nexflare.testhiber.requestModel.Comment;

import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCommentRequestObject extends AbstractRequestObject {

    private String comment;
    private UUID commentId;

}
