package com.nexflare.blog.mapper.User;

import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.CreateNewUserRequestObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateUserRequestToUserMapper implements IRequestToDOMapper<CreateNewUserRequestObject, User> {

    @Override
    public User map(CreateNewUserRequestObject obj) {
        UserType userType = obj.getUserType() != null ? obj.getUserType() : UserType.GENERAL;
        return User.builder()
                .email(obj.getEmail())
                .firstName(obj.getFirstName())
                .lastName(obj.getLastName())
                .password(obj.getPassword())
                .userType(userType)
                .build();
    }
}
