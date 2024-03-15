package com.nexflare.blog.responseModel;
import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.pojo.Blog;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseModel extends AbstractResponseModel {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Blog> blogs;
    private UserType userType;
}
