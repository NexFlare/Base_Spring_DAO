package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dal.LikesDAL;
import com.nexflare.testhiber.dal.UserDAL;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.mapper.LikesRequestToLikeDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Likes.AddLikeService;
import com.nexflare.testhiber.service.Likes.DeleteLikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/like")
public class LikeController {

    IRequestToDOMapper<AddLikeRequestObject, Likes> mapper;
    @Autowired
    public LikeController(IRequestToDOMapper<AddLikeRequestObject, Likes> mapper){
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<Likes> getAllLikes(LikesDAL likesDao) {
        return likesDao.getAll();
    }

    @PostMapping("/")
    public Response addLike(@RequestBody AddLikeRequestObject obj, UserDAL userDAL, HttpServletRequest request, LikesDAL likesDAO, LikesRequestToLikeDOMapper mapper) {
        BaseHandler<AddLikeRequestObject> handler = new AddLikeService(userDAL,request,likesDAO,mapper);
        return handler.handle(obj);
    }

    @DeleteMapping("/")
    public Response deleteLike(@RequestBody AddLikeRequestObject obj, UserDAL userDAL, HttpServletRequest request, LikesDAL likesDAO, LikesRequestToLikeDOMapper mapper) {
        BaseHandler<AddLikeRequestObject> handler = new DeleteLikeService(userDAL,request,likesDAO,mapper);
        return handler.handle(obj);
    }
}
