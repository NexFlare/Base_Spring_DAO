package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private AbstractDAO<Blog, UUID> blogDao;

    @Autowired
    public BlogController(AbstractDAO<Blog, UUID> blogDao) {
        this.blogDao = blogDao;
    }
    @GetMapping("/")
    public List<Blog> getBlogs() {
        return this.blogDao.getAll();
    }

    @PostMapping("/")
    public void addBlog(@RequestBody Blog blog) {
       this.blogDao.add(blog);
    }

    @GetMapping("/{id}")
    public Blog getUserDetail(@PathVariable UUID id) {
        Blog blog = this.blogDao.get(id);
        return blog;
    }
}
