package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    BaseHandler<CreateCommentRequestObject> commentHandler;

    BaseHandler<GetByIdRequestObject> getCommentHandler;

    @Autowired
    public CommentController(BaseHandler<CreateCommentRequestObject> commentHandler,
                             @Qualifier("GetCommentService") BaseHandler<GetByIdRequestObject> getCommentHandler) {
        this.commentHandler = commentHandler;
        this.getCommentHandler = getCommentHandler;
    }

    @PostMapping("/")
    public Response createComment(@RequestBody CreateCommentRequestObject obj) {
        return commentHandler.handle(obj);
    }

    @GetMapping("/{id}")
    public Response getComment(@PathVariable UUID id) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        return getCommentHandler.handle(obj);
    }
}
