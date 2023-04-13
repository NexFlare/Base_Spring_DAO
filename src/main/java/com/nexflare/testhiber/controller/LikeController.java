package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.dao.LikesDAO;
import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.mapper.LikesRequestToLikeDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Likes.AddLikeService;
import com.nexflare.testhiber.service.Likes.DeleteLikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    IRequestToDOMapper<AddLikeRequestObject, Likes> mapper;
    @Autowired
    public LikeController(IRequestToDOMapper<AddLikeRequestObject, Likes> mapper){
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<Likes> getAllLikes(LikesDAO likesDao) {
        return likesDao.getAll();
    }

    @PostMapping("/")
    public Response addLike(@RequestBody AddLikeRequestObject obj, UserDAO userDao, HttpServletRequest request, LikesDAO likesDAO, LikesRequestToLikeDOMapper mapper) {
        BaseHandler<AddLikeRequestObject> handler = new AddLikeService(userDao,request,likesDAO,mapper);
        return handler.handle(obj);
    }

    @DeleteMapping("/")
    public Response deleteLike(@RequestBody AddLikeRequestObject obj, UserDAO userDao, HttpServletRequest request, LikesDAO likesDAO, LikesRequestToLikeDOMapper mapper) {
        BaseHandler<AddLikeRequestObject> handler = new DeleteLikeService(userDao,request,likesDAO,mapper);
        return handler.handle(obj);
    }
}
