package com.nexflare.testhiber.controller;
import com.nexflare.testhiber.dal.CommentDAL;
import com.nexflare.testhiber.dal.UserDAL;
import com.nexflare.testhiber.mapper.Comment.CreateCommentRequestToCommentMapper;
import com.nexflare.testhiber.mapper.Comment.UpdateCommentRequestToCommentMapper;
import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.testhiber.requestModel.Comment.UpdateCommentRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Comment.CreateCommentService;
import com.nexflare.testhiber.service.Comment.GetCommentService;
import com.nexflare.testhiber.service.Comment.UpdateCommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("api/v1/comment")
public class CommentController {

    @PostMapping("/")
    public Response createComment(@RequestBody CreateCommentRequestObject obj, UserDAL userDAL, HttpServletRequest request,
                                  CreateCommentRequestToCommentMapper commentMapper,
                                  CommentDAL commentDAO) {
        BaseHandler<CreateCommentRequestObject> commentHandler = new CreateCommentService(userDAL,request,commentMapper,commentDAO);
        return commentHandler.handle(obj);
    }

    @GetMapping("/{id}")
    public Response getComment(@PathVariable UUID id, UserDAL userDAL, HttpServletRequest request, CommentDAL commentDAO) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        BaseHandler<GetByIdRequestObject> getCommentHandler = new GetCommentService(userDAL,request,commentDAO);
        return getCommentHandler.handle(obj);
    }

    @PutMapping("/")
    public Response getComment(@RequestBody UpdateCommentRequestObject obj, UserDAL userDAL, HttpServletRequest request,
                               CommentDAL commentDAO, UpdateCommentRequestToCommentMapper mapper) {
        BaseHandler<UpdateCommentRequestObject> commentHandler = new UpdateCommentService(userDAL,request,commentDAO,mapper);
        return commentHandler.handle(obj);
    }
}
