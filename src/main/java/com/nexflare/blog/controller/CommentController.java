package com.nexflare.blog.controller;
import com.nexflare.blog.dal.BlogDAL;
import com.nexflare.blog.dal.CommentDAL;
import com.nexflare.blog.dal.UserDAL;
import com.nexflare.blog.mapper.Comment.CreateCommentRequestToCommentMapper;
import com.nexflare.blog.mapper.Comment.UpdateCommentRequestToCommentMapper;
import com.nexflare.blog.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.blog.requestModel.Comment.UpdateCommentRequestObject;
import com.nexflare.blog.requestModel.GetByIdRequestObject;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.BaseHandler;
import com.nexflare.blog.service.Comment.CreateCommentService;
import com.nexflare.blog.service.Comment.DeleteCommentService;
import com.nexflare.blog.service.Comment.GetCommentService;
import com.nexflare.blog.service.Comment.UpdateCommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://10.110.131.218:3000"}, allowCredentials = "true")
@RequestMapping("api/v1/comment")
public class CommentController {

    @PostMapping("/")
    public Response createComment(@RequestBody CreateCommentRequestObject obj, UserDAL userDAL, HttpServletRequest request,
                                  CreateCommentRequestToCommentMapper commentMapper,
                                  CommentDAL commentDAO, BlogDAL blogDAL) {
        BaseHandler<CreateCommentRequestObject> commentHandler = new CreateCommentService(userDAL,request,commentMapper,commentDAO, blogDAL);
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

    @DeleteMapping("/")
    public Response deleteComment(@RequestBody GetByIdRequestObject obj, UserDAL userDAL, HttpServletRequest request,
                                  CommentDAL commentDAO) {
        BaseHandler<GetByIdRequestObject> handler = new DeleteCommentService(userDAL,request,commentDAO);
        return handler.handle(obj);
    }
}
