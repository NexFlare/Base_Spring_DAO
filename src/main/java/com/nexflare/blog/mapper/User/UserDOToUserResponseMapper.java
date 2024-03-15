package com.nexflare.blog.mapper.User;

import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.mapper.IDOToResponseMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.responseModel.UserResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserDOToUserResponseMapper implements IDOToResponseMapper<User, UserResponseModel> {
    @Override
    public UserResponseModel map(User obj) {
        UserType userType = obj.getUserType() != null ? obj.getUserType() : UserType.GENERAL;
        return UserResponseModel.builder()
                .id(obj.getId())
                .email(obj.getEmail())
                .firstName(obj.getFirstName())
                .lastName(obj.getLastName())
                .userType(obj.getUserType())
                .build();
    }
}
