package com.nexflare.testhiber.mapper.User;

import com.nexflare.testhiber.enums.UserType;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.CreateNewUserRequestObject;
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
