package com.nexflare.testhiber.requestModel;

import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRequestObject extends AbstractRequestObject {

    private UUID id;
}
