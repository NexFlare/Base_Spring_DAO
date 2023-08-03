package com.nexflare.testhiber.mapper.User;

import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.User.GetUserRequestObject;
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
