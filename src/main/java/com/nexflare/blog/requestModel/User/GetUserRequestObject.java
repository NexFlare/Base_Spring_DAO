package com.nexflare.blog.requestModel.User;

import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetUserRequestObject extends AbstractRequestObject {
    private String email;
    private String password;
    private UUID id;
}
