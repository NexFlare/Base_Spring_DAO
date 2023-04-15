package com.nexflare.testhiber.controller;
import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.dao.CommentDAO;
import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.mapper.Comment.CreateCommentRequestToCommentMapper;
import com.nexflare.testhiber.mapper.Comment.UpdateCommentRequestToCommentMapper;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Comment.CreateCommentRequestObject;
import com.nexflare.testhiber.requestModel.Comment.UpdateCommentRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Comment.CreateCommentService;
import com.nexflare.testhiber.service.Comment.GetCommentService;
import com.nexflare.testhiber.service.Comment.UpdateCommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("api/v1/comment")
public class CommentController {

    @PostMapping("/")
    public Response createComment(@RequestBody CreateCommentRequestObject obj, UserDAO userDao, HttpServletRequest request,
                                  CreateCommentRequestToCommentMapper commentMapper,
                                  CommentDAO commentDAO) {
        BaseHandler<CreateCommentRequestObject> commentHandler = new CreateCommentService(userDao,request,commentMapper,commentDAO);
        return commentHandler.handle(obj);
    }

    @GetMapping("/{id}")
    public Response getComment(@PathVariable UUID id, UserDAO userDao, HttpServletRequest request, CommentDAO commentDAO) {
        GetByIdRequestObject obj = new GetByIdRequestObject(id);
        BaseHandler<GetByIdRequestObject> getCommentHandler = new GetCommentService(userDao,request,commentDAO);
        return getCommentHandler.handle(obj);
    }

    @PutMapping("/")
    public Response getComment(@RequestBody UpdateCommentRequestObject obj, UserDAO userDao, HttpServletRequest request,
                               CommentDAO commentDAO, UpdateCommentRequestToCommentMapper mapper) {
        BaseHandler<UpdateCommentRequestObject> commentHandler = new UpdateCommentService(userDao,request,commentDAO,mapper);
        return commentHandler.handle(obj);
    }
}
