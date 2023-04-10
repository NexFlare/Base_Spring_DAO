package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.dao.LikesDAO;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
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
    public void addLike(@RequestBody AddLikeRequestObject obj, LikesDAO likesDao) {
        Likes likeObj = this.mapper.map(obj);
        likesDao.add(likeObj);
    }

    @DeleteMapping("/")
    public void deleteLike(@RequestBody AddLikeRequestObject obj, LikesDAO likesDao) {
        Likes likeObj = this.mapper.map(obj);
        Map<String, Object> map = new HashMap<>();
        map.put("blog", likeObj.getBlog());
        Likes like = likesDao.getUniqueElementByQuery(map);
        likesDao.delete(like);

//        likesDao.delete(likeObj);
    }
}
