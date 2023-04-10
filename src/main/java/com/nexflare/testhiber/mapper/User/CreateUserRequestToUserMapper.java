package com.nexflare.testhiber.mapper.User;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
import org.springframework.stereotype.Component;

@Component
public class CreateUserRequestToUserMapper implements IRequestToDOMapper<CreateNewUserRequestObject, User> {

    @Override
    public User map(CreateNewUserRequestObject obj) {
        return User.builder()
                .email(obj.getEmail())
                .firstName(obj.getFirstName())
                .lastName(obj.getLastName())
                .password(obj.getPassword()).build();
    }
}
