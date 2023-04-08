package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
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
    AbstractDAO<Likes, UUID> likesDao;
    @Autowired
    public LikeController(IRequestToDOMapper<AddLikeRequestObject, Likes> mapper, AbstractDAO<Likes, UUID> likesDao){
        this.mapper = mapper;
        this.likesDao = likesDao;
    }

    @GetMapping("/")
    public List<Likes> getAllLikes() {
        return this.likesDao.getAll();
    }

    @PostMapping("/")
    public void addLike(@RequestBody AddLikeRequestObject obj) {
        Likes likeObj = this.mapper.map(obj);
        likesDao.add(likeObj);
    }

    @DeleteMapping("/")
    public void deleteLike(@RequestBody AddLikeRequestObject obj) {
        Likes likeObj = this.mapper.map(obj);
        Map<String, Object> map = new HashMap<>();
        map.put("blog", likeObj.getBlog());
        Likes like = this.likesDao.getUniqueElementByQuery(map);
        this.likesDao.delete(like);

//        likesDao.delete(likeObj);
    }
}
