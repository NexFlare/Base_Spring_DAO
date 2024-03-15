package com.nexflare.blog.controller;


import com.nexflare.blog.dal.LikesDAL;
import com.nexflare.blog.dal.UserDAL;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.mapper.LikesRequestToLikeDOMapper;
import com.nexflare.blog.pojo.Likes;
import com.nexflare.blog.requestModel.AddLikeRequestObject;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.BaseHandler;
import com.nexflare.blog.service.Likes.AddLikeService;
import com.nexflare.blog.service.Likes.DeleteLikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://10.110.131.218:3000"}, allowCredentials = "true")
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
