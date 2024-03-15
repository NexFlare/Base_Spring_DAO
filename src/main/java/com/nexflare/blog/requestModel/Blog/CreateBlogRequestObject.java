package com.nexflare.blog.requestModel.Blog;

import com.nexflare.blog.enums.BlogType;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.AbstractRequestObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBlogRequestObject extends AbstractRequestObject {

    /**
     * {
     *     "title" : "Test title Again",
     *     "text" : "Test Title",
     *     "location" : "Boston",
     *     "user" : {
     *         "id" : "84ebbe01-503e-4331-8157-1e79d440c370"
     *     }
     *
     * }
     */

    private String title;
    private String text;
    private String location;
    private User user;
    private BlogType type;
    private String imageUrl;
}
