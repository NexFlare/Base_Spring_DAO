package com.nexflare.blog.mapper.User;

import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.User.GetUserRequestObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class GetUserRequestToUserMapper implements IRequestToDOMapper<GetUserRequestObject, User> {
    @Override
    public User map(GetUserRequestObject obj) {
        return User.builder()
                .id(obj.getId())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .build();
    }
}
