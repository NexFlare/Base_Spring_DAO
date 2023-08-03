package com.nexflare.testhiber.requestModel.Blog;

import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;
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
