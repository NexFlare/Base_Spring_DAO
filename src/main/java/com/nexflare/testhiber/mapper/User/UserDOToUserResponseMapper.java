package com.nexflare.testhiber.mapper.User;

import com.nexflare.testhiber.mapper.IDOToResponseMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.responseModel.UserResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserDOToUserResponseMapper implements IDOToResponseMapper<User, UserResponseModel> {
    @Override
    public UserResponseModel map(User obj) {
        return UserResponseModel.builder()
                .id(obj.getId())
                .email(obj.getEmail())
                .firstName(obj.getFirstName())
                .lastName(obj.getLastName())
//                .blogs(obj.getBlogs())
                .build();
    }
}
