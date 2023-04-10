package com.nexflare.testhiber.requestModel.User;

import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewUserRequestObject extends AbstractRequestObject {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
