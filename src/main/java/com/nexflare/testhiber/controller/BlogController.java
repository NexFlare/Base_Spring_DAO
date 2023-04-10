package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.BlogDAO;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.Blog.CreateBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

//    private AbstractDAO<Blog, UUID> blogDao;

    private CreateBlogService createBlogService;

    @Autowired
    public BlogController(CreateBlogService createBlogService) {
        this.createBlogService = createBlogService;
    }
    @GetMapping("/")
    public List<Blog> getBlogs(BlogDAO blogDao) {
        return blogDao.getAll();
    }

    @PostMapping("/")
    public Response addBlog(@RequestBody CreateBlogRequestObject blog) {
        return createBlogService.handle(blog);
    }

    @GetMapping("/{id}")
    public Blog getUserDetail(@PathVariable UUID id, BlogDAO blogDao) {
        Blog blog = blogDao.get(id);
        return blog;
    }
}
