package com.nexflare.blog.requestModel.User;

import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequestObject extends AbstractRequestObject {
    private String email;
    private String oldPassword;
    private String newPassword;
}
