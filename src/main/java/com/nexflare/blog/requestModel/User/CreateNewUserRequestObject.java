package com.nexflare.blog.requestModel.User;

import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewUserRequestObject extends AbstractRequestObject {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserType userType;
}
