package com.nexflare.blog.requestModel;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddLikeRequestObject extends AbstractRequestObject{
    private UUID userId;
    private UUID blogId;

}
