package com.nexflare.blog.responseModel.Comment;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CommentResponseModel {
    private String comment;
    private UUID id;

    private UUID blogId;

    private UUID userId;

    private Date timestamp;

}
